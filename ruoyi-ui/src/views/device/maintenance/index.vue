<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备编号" prop="deviceCode">
        <el-input
          v-model="queryParams.deviceCode"
          placeholder="请输入设备编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维护类型" prop="maintenanceType">
        <el-select v-model="queryParams.maintenanceType" placeholder="请选择维护类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_device_maintenance_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="maintenanceStatus">
        <el-select v-model="queryParams.maintenanceStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_device_maintenance_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:maintenance:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:maintenance:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:maintenance:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:maintenance:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="maintenanceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备编号" align="center
      " prop="deviceCode" />
      <el-table-column label="维护类型" align="center" prop="maintenanceType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_device_maintenance_type" :value="scope.row.maintenanceType"/>
        </template>
      </el-table-column>
      <el-table-column label="故障描述" align="center" prop="faultDescription" :show-overflow-tooltip="true" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="maintenanceStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_device_maintenance_status" :value="scope.row.maintenanceStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="维护结果" align="center" prop="maintenanceResult" :show-overflow-tooltip="true" />
      <el-table-column label="维护费用" align="center" prop="maintenanceCost" />
      <el-table-column label="维护人员" align="center" prop="maintainer" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:maintenance:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:maintenance:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改设备维护记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备" prop="deviceId">
          <el-select v-model="form.deviceId" placeholder="请选择设备" @change="handleDeviceChange">
            <el-option
              v-for="device in deviceOptions"
              :key="device.deviceId"
              :label="device.deviceName"
              :value="device.deviceId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="维护类型" prop="maintenanceType">
          <el-select v-model="form.maintenanceType" placeholder="请选择维护类型">
            <el-option
              v-for="dict in dict.type.sys_device_maintenance_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="故障描述" prop="faultDescription">
          <el-input v-model="form.faultDescription" type="textarea" placeholder="请输入故障描述" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="maintenanceStatus">
          <el-select v-model="form.maintenanceStatus" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.sys_device_maintenance_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="维护结果" prop="maintenanceResult">
          <el-input v-model="form.maintenanceResult" type="textarea" placeholder="请输入维护结果" />
        </el-form-item>
        <el-form-item label="维护费用" prop="maintenanceCost">
          <el-input v-model="form.maintenanceCost" placeholder="请输入维护费用" />
        </el-form-item>
        <el-form-item label="维护人员" prop="maintainer">
          <el-input v-model="form.maintainer" placeholder="请输入维护人员" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMaintenance, getMaintenance, delMaintenance, addMaintenance, updateMaintenance } from "@/api/device/maintenance";
import { listDevice, updateDevice } from "@/api/device/list";

export default {
  name: "Maintenance",
  dicts: ['sys_device_maintenance_status', 'sys_device_maintenance_type'],
  data() {
    return {
      // 设备选项
      deviceOptions: [],
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
      // 设备维护记录表格数据
      maintenanceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceCode: null,
        maintenanceType: null,
        maintenanceStatus: null,
        planTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deviceCode: [
          { required: true, message: "设备不能为空", trigger: "change" }
        ],
        maintenanceType: [
          { required: true, message: "维护类型不能为空", trigger: "blur" }
        ],
        faultDescription: [
          { required: true, message: "故障描述不能为空", trigger: "blur" },
          { min: 1, max: 500, message: "故障描述长度必须在1到500个字符之间", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "开始时间不能为空", trigger: "change" },
          { validator: (rule, value, callback) => {
            if (value && this.form.endTime && value > this.form.endTime) {
              callback(new Error("开始时间不能晚于结束时间"));
            } else {
              callback();
            }
          }, trigger: "change" }
        ],
        endTime: [
          { required: true, message: "结束时间不能为空", trigger: "change" },
          { validator: (rule, value, callback) => {
            if (value && this.form.startTime && value < this.form.startTime) {
              callback(new Error("结束时间不能早于开始时间"));
            } else {
              callback();
            }
          }, trigger: "change" }
        ],
        maintenanceStatus: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        maintenanceResult: [
          { required: false, max: 500, message: "维护结果长度不能超过500个字符", trigger: "blur" }
        ],
        maintenanceCost: [
          { required: false, message: "维护费用不能为空", trigger: "blur" },
          { pattern: /^\d+(\.\d{1,2})?$/, message: "维护费用必须为数字，最多保留两位小数", trigger: "blur" },
          { validator: (rule, value, callback) => {
            if (value && value < 0) {
              callback(new Error("维护费用不能为负数"));
            } else {
              callback();
            }
          }, trigger: "blur" }
        ],
        remark: [
          { max: 500, message: "备注长度不能超过500个字符", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getDeviceOptions();
    this.getList();
  },
  methods: {
    /** 获取设备选项列表 */
    getDeviceOptions() {
      listDevice().then(response => {
        this.deviceOptions = response.rows;
      });
    },
    /** 设备选择框变更事件 */
    handleDeviceChange(value) {
      // 可以在这里添加其他逻辑
    },
    /** 更新设备状态 */
    updateDeviceStatus(deviceId, maintenanceStatus) {
      let deviceStatus;
      switch(maintenanceStatus) {
        case '1': // 待处理
          deviceStatus = '2'; // 待维修
          break;
        case '2': // 进行中
          deviceStatus = '3'; // 维修中
          break;
        case '3': // 已完成
          deviceStatus = '1'; // 正常
          break;
        case '4': // 已取消
          deviceStatus = '1'; // 正常
          break;
        default:
          return; // 其他状态不更新设备状态
      }
      updateDevice({
        deviceId: deviceId,
        deviceStatus: deviceStatus
      }).then(() => {
        this.getList();
      }).catch(error => {
        this.$modal.msgError("更新设备状态失败：" + error.message);
      });
    },
    /** 查询设备维护记录列表 */
    getList() {
      this.loading = true;
      listMaintenance(this.queryParams).then(response => {
        this.maintenanceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        maintenanceId: null,
        deviceCode: null,
        maintenanceType: null,
        faultDescription: null,
        startTime: null,
        endTime: null,
        maintenanceStatus: null,
        maintenanceResult: null,
        maintenanceCost: null,
        remark: null
      };
      this.resetForm("form");
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
      this.ids = selection.map(item => item.maintenanceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备维护记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const maintenanceId = row.maintenanceId || this.ids
      getMaintenance(maintenanceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备维护记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.maintenanceId != null) {
            updateMaintenance(this.form).then(response => {
              this.updateDeviceStatus(this.form.deviceId, this.form.maintenanceStatus);
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).catch(error => {
              this.$modal.msgError("修改失败：" + error.message);
            });
          } else {
            addMaintenance(this.form).then(response => {
              this.updateDeviceStatus(this.form.deviceId, this.form.maintenanceStatus);
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).catch(error => {
              this.$modal.msgError("新增失败：" + error.message);
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const maintenanceIds = row.maintenanceId || this.ids;
      this.$modal.confirm('是否确认删除设备维护记录编号为"' + maintenanceIds + '"的数据项？').then(function() {
        return delMaintenance(maintenanceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/maintenance/export', {
        ...this.queryParams
      }, `maintenance_${new Date().getTime()}.xlsx`);
    }
  }
};
</script>

<style scoped>
/* Add your custom styles here */
</style>