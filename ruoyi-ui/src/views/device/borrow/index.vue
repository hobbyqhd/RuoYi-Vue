<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备编号" prop="deviceCode">
        <el-input v-model="queryParams.deviceCode" placeholder="请输入设备编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="借用人" prop="borrower">
        <el-input v-model="queryParams.borrower" placeholder="请输入借用人" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="借用状态" prop="borrowStatus">
        <el-select v-model="queryParams.borrowStatus" placeholder="借用状态" clearable>
          <el-option v-for="dict in dict.type.sys_device_borrow_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['device:borrow:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['device:borrow:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['device:borrow:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['device:borrow:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="borrowList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="借用记录ID" align="center" prop="borrowId" />
      <el-table-column label="设备编号" align="center" prop="deviceCode" />
      <el-table-column label="借用人" align="center" prop="borrower" />
      <el-table-column label="借用时间" align="center" prop="borrowTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.borrowTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预计归还时间" align="center" prop="expectedReturnTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expectedReturnTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际归还时间" align="center" prop="actualReturnTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.actualReturnTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="借用状态" align="center" prop="borrowStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_device_borrow_status" :value="scope.row.borrowStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['device:borrow:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['device:borrow:remove']">删除</el-button>
          <el-button size="mini" type="text" icon="el-icon-check" @click="handleApprove(scope.row)" v-if="scope.row.borrowStatus === '0'" v-hasPermi="['device:borrow:edit']">审批</el-button>
          <el-button size="mini" type="text" icon="el-icon-back" @click="handleReturn(scope.row)" v-if="scope.row.borrowStatus === '1'" v-hasPermi="['device:borrow:edit']">归还</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改设备借用记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
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
          <el-date-picker clearable
            v-model="form.expectedReturnTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择预计归还时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="借用用途" prop="borrowPurpose">
          <el-input v-model="form.borrowPurpose" type="textarea" placeholder="请输入借用用途" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 审批对话框 -->
    <el-dialog title="借用审批" :visible.sync="approveOpen" width="500px" append-to-body>
      <el-form ref="approveForm" :model="approveForm" :rules="approveRules" label-width="80px">
        <el-form-item label="审批结果" prop="borrowStatus">
          <el-radio-group v-model="approveForm.borrowStatus">
            <el-radio label="1">同意</el-radio>
            <el-radio label="2">拒绝</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审批意见" prop="approveRemark">
          <el-input v-model="approveForm.approveRemark" type="textarea" placeholder="请输入审批意见" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitApproveForm">确 定</el-button>
        <el-button @click="cancelApprove">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBorrow, getBorrow, delBorrow, addBorrow, updateBorrow, returnDevice } from "@/api/device/borrow";
import { listDevice } from "@/api/device/list";

export default {
  name: "SysDeviceBorrow",
  dicts: ['sys_device_borrow_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 设备借用记录表格数据
      borrowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示审批弹出层
      approveOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceId: null,
        borrower: null,
        borrowStatus: null
      },
      // 表单参数
      form: {},
      // 审批表单参数
      approveForm: {},
      // 表单校验
      rules: {
        deviceCode: [
          { required: true, message: "设备编号不能为空", trigger: "blur" }
        ],
        borrower: [
          { required: true, message: "借用人不能为空", trigger: "blur" }
        ],
        expectedReturnTime: [
          { required: true, message: "预计归还时间不能为空", trigger: "blur" }
        ],
        borrowPurpose: [
          { required: true, message: "借用用途不能为空", trigger: "blur" }
        ]
      },
      // 审批表单校验
      approveRules: {
        borrowStatus: [
          { required: true, message: "审批结果不能为空", trigger: "change" }
        ],
        approveRemark: [
          { required: true, message: "审批意见不能为空", trigger: "blur" }
        ]
      },
      // 设备选项
      deviceOptions: []
    };
  },
  created() {
    this.getList();
    this.getDeviceOptions();
  },
  methods: {
    /** 查询设备借用记录列表 */
    getList() {
      this.loading = true;
      listBorrow(this.queryParams).then(response => {
        this.borrowList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 获取可借用设备列表 */
    getDeviceOptions() {
      listDevice({ deviceStatus: '1' }).then(response => {
        this.deviceOptions = response.rows;
      });
    },
    /** 设备选择框变化事件 */
    handleDeviceChange(value) {
      const device = this.deviceOptions.find(item => item.deviceId === value);
      if (device) {
        this.form.deviceName = device.deviceName;
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 取消审批
    cancelApprove() {
      this.approveOpen = false;
      this.resetApproveForm();
    },
    // 表单重置
    reset() {
      this.form = {
        borrowId: null,
        deviceId: null,
        borrower: null,
        borrowTime: null,
        expectedReturnTime: null,
        actualReturnTime: null,
        borrowStatus: "0",
        borrowPurpose: null,
        approver: null,
        approveTime: null,
        approveRemark: null
      };
      this.resetForm("form");
    },
    // 审批表单重置
    resetApproveForm() {
      this.approveForm = {
        borrowId: null,
        borrowStatus: null,
        approveRemark: null
      };
      this.resetForm("approveForm");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.borrowId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备借用记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const borrowId = row.borrowId || this.ids[0]
      getBorrow(borrowId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备借用记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.borrowId != null) {
            updateBorrow(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBorrow(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('device/borrow/export', {
        ...this.queryParams
      }, `device_borrow_${new Date().getTime()}.xlsx`);
    },
    /** 审批按钮操作 */
    handleApprove(row) {
      this.resetApproveForm();
      this.approveForm.borrowId = row.borrowId;
      this.approveOpen = true;
    },
    /** 提交审批 */
    submitApproveForm() {
      this.$refs["approveForm"].validate(valid => {
        if (valid) {
          updateBorrow(this.approveForm).then(response => {
            this.$modal.msgSuccess("审批成功");
            this.approveOpen = false;
            this.getList();
          });
        }
      });
    },
    /** 归还按钮操作 */
    handleReturn(row) {
      const borrowId = row.borrowId;
      this.$modal.confirm('是否确认归还该设备？').then(function() {
        return returnDevice(borrowId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("归还成功");
      }).catch(() => {});
    }
  }
};
</script>