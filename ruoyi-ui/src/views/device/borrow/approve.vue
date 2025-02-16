<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="设备ID">
        <span>{{ form.deviceId }}</span>
      </el-form-item>
      <el-form-item label="借用人">
        <span>{{ form.borrower }}</span>
      </el-form-item>
      <el-form-item label="借用时间">
        <span>{{ parseTime(form.borrowTime) }}</span>
      </el-form-item>
      <el-form-item label="预计归还时间">
        <span>{{ parseTime(form.expectedReturnTime) }}</span>
      </el-form-item>
      <el-form-item label="借用用途">
        <span>{{ form.borrowPurpose }}</span>
      </el-form-item>
      <el-form-item label="审批结果" prop="borrowStatus">
        <el-radio-group v-model="form.borrowStatus">
          <el-radio label="1">批准</el-radio>
          <el-radio label="2">拒绝</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="审批备注" prop="approveRemark">
        <el-input
          v-model="form.approveRemark"
          type="textarea"
          placeholder="请输入审批备注"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getBorrow, approveBorrow } from "@/api/device/borrow";
import { updateDevice } from "@/api/device/list";

export default {
  name: "BorrowApprove",
  data() {
    return {
      // 表单参数
      form: {
        borrowId: undefined,
        deviceId: undefined,
        borrower: undefined,
        borrowTime: undefined,
        expectedReturnTime: undefined,
        borrowPurpose: undefined,
        borrowStatus: "1",
        approveRemark: undefined
      },
      // 表单校验
      rules: {
        borrowStatus: [
          { required: true, message: "请选择审批结果", trigger: "change" }
        ],
        approveRemark: [
          { required: true, message: "请输入审批备注", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    const borrowId = this.$route.query.borrowId;
    if (borrowId) {
      this.getBorrowInfo(borrowId);
    }
  },
  methods: {
    /** 获取借用记录详细信息 */
    getBorrowInfo(borrowId) {
      getBorrow(borrowId).then(response => {
        this.form = response.data;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          approveBorrow(this.form).then(response => {
            // 如果审批通过，更新设备状态为借出
            if (this.form.borrowStatus === "1") {
              updateDevice({
                deviceId: this.form.deviceId,
                status: "1" // 更新设备状态为借出
              }).then(() => {
                this.$modal.msgSuccess("审批成功，设备状态已更新");
                this.cancel();
              });
            } else {
              this.$modal.msgSuccess("审批成功");
              this.cancel();
            }
          });
        }
      });
    },
    /** 取消按钮 */
    cancel() {
      this.$router.push({ path: "/device/borrow" });
    }
  }
};
</script>