<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="设备" prop="deviceId">
        <el-select v-model="form.deviceId" placeholder="请选择设备" @change="handleDeviceChange">
          <el-option
            v-for="item in deviceOptions"
            :key="item.deviceId"
            :label="item.deviceName"
            :value="item.deviceId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="借用人" prop="borrower">
        <el-input v-model="form.borrower" placeholder="请输入借用人" />
      </el-form-item>
      <el-form-item label="预计归还时间" prop="expectedReturnTime">
        <el-date-picker
          v-model="form.expectedReturnTime"
          type="datetime"
          placeholder="选择预计归还时间"
          value-format="yyyy-MM-dd HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="借用用途" prop="borrowPurpose">
        <el-input
          v-model="form.borrowPurpose"
          type="textarea"
          placeholder="请输入借用用途"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input
          v-model="form.remark"
          type="textarea"
          placeholder="请输入备注"
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
import { getBorrow, addBorrow, updateBorrow } from "@/api/device/borrow";
import { listDevice } from "@/api/device/list";

export default {
  name: "BorrowForm",
  data() {
    return {
      // 设备选项列表
      deviceOptions: [],
      // 表单参数
      form: {
        borrowId: undefined,
        deviceId: undefined,
        borrower: undefined,
        expectedReturnTime: undefined,
        borrowPurpose: undefined,
        remark: undefined
      },
      // 表单校验
      rules: {
        deviceId: [
          { required: true, message: "设备ID不能为空", trigger: "blur" }
        ],
        borrower: [
          { required: true, message: "借用人不能为空", trigger: "blur" }
        ],
        expectedReturnTime: [
          { required: true, message: "预计归还时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getDeviceList();
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
    /** 获取可借用设备列表 */
    getDeviceList() {
      listDevice({ status: "0" }).then(response => {
        this.deviceOptions = response.rows;
      });
    },
    /** 设备选择变更 */
    handleDeviceChange(value) {
      const device = this.deviceOptions.find(item => item.deviceId === value);
      if (device) {
        this.form.deviceName = device.deviceName;
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.borrowId) {
            updateBorrow(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.cancel();
            });
          } else {
            addBorrow(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.cancel();
            });
          }
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