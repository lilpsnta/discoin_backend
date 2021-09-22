<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="账户名称">
              <a-input placeholder="请输入账户名称" v-model="queryParam.userId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('账户')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <account-modal ref="modalForm" @ok="modalFormOk"></account-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import AccountModal from './modules/AccountModal'

  export default {
    name: 'AccountList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      AccountModal
    },
    data () {
      return {
        description: '账户管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'账户地址',
            align:"center",
            dataIndex: 'token'
          },
          {
            title:'账户名称',
            align:"center",
            dataIndex: 'userId'
          },
          {
            title:'账户余额',
            align:"center",
            dataIndex: 'balance'
          },
          {
            title:'冻结金额',
            align:"center",
            dataIndex: 'lockedAmount'
          },
          {
            title:'累计金额',
            align:"center",
            dataIndex: 'totalAmount'
          },
          {
            title:'今日收益',
            align:"center",
            dataIndex: 'todayProfit'
          },
          {
            title:'预计收益',
            align:"center",
            dataIndex: 'predictProfit'
          },
          {
            title:'收益周期',
            align:"center",
            dataIndex: 'profitTerm'
          },
          {
            title:'收益类型',
            align:"center",
            dataIndex: 'profitType_dictText'
          },
          {
            title:'收益占比',
            align:"center",
            dataIndex: 'profitRadio'
          },
          {
            title:'固定收益',
            align:"center",
            dataIndex: 'fixedProfit'
          },
          {
            title:'每日提币上限',
            align:"center",
            dataIndex: 'dayLimitAmount'
          },
          {
            title:'管理费',
            align:"center",
            dataIndex: 'managementFee'
          },
          {
            title:'手续费',
            align:"center",
            dataIndex: 'tradeFee'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/account/list",
          delete: "/account/delete",
          deleteBatch: "/account/deleteBatch",
          exportXlsUrl: "/account/exportXls",
          importExcelUrl: "account/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'token',text:'账户地址',dictCode:''})
        fieldList.push({type:'string',value:'userId',text:'账户名称',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'balance',text:'账户余额',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'lockedAmount',text:'冻结金额',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'totalAmount',text:'累计金额',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'todayProfit',text:'今日收益',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'predictProfit',text:'预计收益',dictCode:''})
        fieldList.push({type:'int',value:'profitTerm',text:'收益周期',dictCode:''})
        fieldList.push({type:'string',value:'profitType',text:'收益类型',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'profitRadio',text:'收益占比',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'fixedProfit',text:'固定收益',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'dayLimitAmount',text:'每日提币上限',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'managementFee',text:'管理费',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'tradeFee',text:'手续费',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>