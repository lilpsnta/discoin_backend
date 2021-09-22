<template>
  <div class="page-header-index-wide">
    <a-row :gutter="24">
      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="项目数量" total="16">
          <a-tooltip title="指标说明" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <div>
            <trend flag="up" style="margin-right: 16px">
              <span slot="term">管理项目</span>
              12
            </trend>
            <trend flag="down">
              <span slot="term">参与</span>
              4
            </trend>
          </div>
          <template slot="footer">进入项目列表<span></span></template>
        </chart-card>
      </a-col>

      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="巡检数量" :total="45 | NumberFormat">
          <a-tooltip title="指标说明" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <div>
            <trend flag="up" style="margin-right: 16px">
              <span slot="term">当日巡检</span>
              12
            </trend>
            <trend flag="down">
              <span slot="term">累计巡检</span>
              389
            </trend>
          </div>
          <template slot="footer"
            >当日巡检数量<span> {{ '1234' | NumberFormat }}</span></template
          >
        </chart-card>
      </a-col>

      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="项目成果" :total="75 | NumberFormat">
          <a-tooltip title="指标说明" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <div>
            <trend flag="up" style="margin-right: 16px">
              <span slot="term">待上传</span>
              4
            </trend>
            <trend flag="down">
              <span slot="term">已上传</span>
              32
            </trend>
          </div>
          <template slot="footer">累计上传 <span>452</span>份</template>
        </chart-card>
      </a-col>

      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="待办事项" total="12">
          <a-tooltip title="指标说明" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <div>
            <trend flag="down">
              <span slot="term">待审批</span>
              11
            </trend>
          </div>
          <template slot="footer">
            <trend flag="down" style="margin-right: 16px">
              <span slot="term">审核</span>
              1
            </trend>
          </template>
        </chart-card>
      </a-col>
    </a-row>

    <a-row :gutter="24">
      <a-col :sm="24" :md="12" :xl="6">
        <a-card :loading="loading" title="项目清单" style="height:580px;" headStyle="height:20px;line-height:20px;">
          <div>
            <a-select
              show-search
              placeholder="请选择项目"
              option-filter-prop="children"
              style="width: 100%"
            >
              <a-select-option value="1">
                上海北京东路建设施工项目
              </a-select-option>
              <a-select-option value="2">
                北京三环新四路道口实施项目
              </a-select-option>
              <a-select-option value="3">
                南京北沿山街道施工项目
              </a-select-option>
            </a-select>
          </div>
          <div>
            <div>
              <a-row type="flex" class="project_row">
                <a-col :xl="6">
                  <div class="project_left">
                    <span class="project_manager">李朝阳</span></br>
                    <span class="project_result">成果:20%</span>
                  </div>
                </a-col>
                <a-col :xl="18">
                  <div class="project_name">上海北京东路建设施工项目</div>
                  <div class="project_info">上海浦东新区北京东路13号</div>
                  <div class="project_info">沥青路面结构</div>
                  <div class="project_info">服务期限：3年</div>
                </a-col>
              </a-row>

              <a-row type="flex" class="project_row">
                <a-col :xl="6">
                  <div class="project_left" style="background-color:#5F76FD;">
                    <span class="project_manager">李朝阳</span></br>
                    <span class="project_result">成果:20%</span>
                  </div>
                </a-col>
                <a-col :xl="18">
                  <div class="project_name">上海北京东路建设施工项目</div>
                  <div class="project_info">上海浦东新区北京东路13号</div>
                  <div class="project_info">沥青路面结构</div>
                  <div class="project_info">服务期限：3年</div>
                </a-col>
              </a-row>

              <a-row type="flex" class="project_row">
                <a-col :xl="6">
                  <div class="project_left" style="background-color:#C95FFD;">
                    <span class="project_manager">李朝阳</span></br>
                    <span class="project_result">成果:20%</span>
                  </div>
                </a-col>
                <a-col :xl="18">
                  <div class="project_name">上海北京东路建设施工项目</div>
                  <div class="project_info">上海浦东新区北京东路13号</div>
                  <div class="project_info">沥青路面结构</div>
                  <div class="project_info">服务期限：3年</div>
                </a-col>
              </a-row>

              <a-row type="flex" class="project_row">
                <a-col :xl="6">
                  <div class="project_left" style="background-color:#FD835F;">
                    <span class="project_manager">李朝阳</span></br>
                    <span class="project_result">成果:20%</span>
                  </div>
                </a-col>
                <a-col :xl="18">
                  <div class="project_name">上海北京东路建设施工项目</div>
                  <div class="project_info">上海浦东新区北京东路13号</div>
                  <div class="project_info">沥青路面结构</div>
                  <div class="project_info">服务期限：3年</div>
                </a-col>
              </a-row>


            </div>
          </div>
        </a-card>
        
      </a-col>
      <a-col :sm="24" :md="12" :xl="18">
        <a-card :loading="loading" title="项目信息" style="height:280px;" headStyle="height:20px;line-height:20px;">
          <div style="height:200px;">
            <a-row>
              <a-col :xl="6">
                <a-row type="flex" class="member_row">
                  <a-col :xl="8">
                    <div class="member_left">
                      <img src="../../assets/manager.png" style="width:70px;height:80px;"/>
                    </div>
                  </a-col>
                  <a-col :xl="16">
                    <div class="member_name">陈建东</div>
                    <div class="member_info">项目经理</div>
                    <div class="member_info">博士</div>
                    <div class="member_info">10年工作经验</div>
                  </a-col>
                </a-row>
              </a-col>
              

              <a-col :xl="6">
                <a-row type="flex" class="member_row">
                  <a-col :xl="8">
                    <div class="member_left">
                      <img src="../../assets/manager.png" style="width:70px;height:80px;"/>
                    </div>
                  </a-col>
                  <a-col :xl="16">
                    <div class="member_name">陈建东</div>
                    <div class="member_info">项目经理</div>
                    <div class="member_info">博士</div>
                    <div class="member_info">10年工作经验</div>
                  </a-col>
                </a-row>
              </a-col>

              <a-col :xl="6">
                <a-row type="flex" class="member_row">
                  <a-col :xl="8">
                    <div class="member_left">
                      <img src="../../assets/manager.png" style="width:70px;height:80px;"/>
                    </div>
                  </a-col>
                  <a-col :xl="16">
                    <div class="member_name">陈建东</div>
                    <div class="member_info">项目经理</div>
                    <div class="member_info">博士</div>
                    <div class="member_info">10年工作经验</div>
                  </a-col>
                </a-row>
              </a-col>

              <a-col :xl="6">
                <a-row type="flex" class="member_row">
                  <a-col :xl="8">
                    <div class="member_left">
                      <img src="../../assets/manager.png" style="width:70px;height:80px;"/>
                    </div>
                  </a-col>
                  <a-col :xl="16">
                    <div class="member_name">陈建东</div>
                    <div class="member_info">项目经理</div>
                    <div class="member_info">博士</div>
                    <div class="member_info">10年工作经验</div>
                  </a-col>
                </a-row>
              </a-col>

              <a-col :xl="6">
                <a-row type="flex" class="member_row">
                  <a-col :xl="8">
                    <div class="member_left">
                      <img src="../../assets/manager.png" style="width:70px;height:80px;"/>
                    </div>
                  </a-col>
                  <a-col :xl="16">
                    <div class="member_name">陈建东</div>
                    <div class="member_info">项目经理</div>
                    <div class="member_info">博士</div>
                    <div class="member_info">10年工作经验</div>
                  </a-col>
                </a-row>
              </a-col>
              

              <a-col :xl="6">
                <a-row type="flex" class="member_row">
                  <a-col :xl="8">
                    <div class="member_left">
                      <img src="../../assets/manager.png" style="width:70px;height:80px;"/>
                    </div>
                  </a-col>
                  <a-col :xl="16">
                    <div class="member_name">陈建东</div>
                    <div class="member_info">项目经理</div>
                    <div class="member_info">博士</div>
                    <div class="member_info">10年工作经验</div>
                  </a-col>
                </a-row>
              </a-col>

              <a-col :xl="6">
                <a-row type="flex" class="member_row">
                  <a-col :xl="8">
                    <div class="member_left">
                      <img src="../../assets/manager.png" style="width:70px;height:80px;"/>
                    </div>
                  </a-col>
                  <a-col :xl="16">
                    <div class="member_name">陈建东</div>
                    <div class="member_info">项目经理</div>
                    <div class="member_info">博士</div>
                    <div class="member_info">10年工作经验</div>
                  </a-col>
                </a-row>
              </a-col>
              

              <a-col :xl="6">
                <a-row type="flex" class="member_row">
                  <a-col :xl="8">
                    <div class="member_left">
                      <img src="../../assets/manager.png" style="width:70px;height:80px;"/>
                    </div>
                  </a-col>
                  <a-col :xl="16">
                    <div class="member_name">陈建东</div>
                    <div class="member_info">项目经理</div>
                    <div class="member_info">博士</div>
                    <div class="member_info">10年工作经验</div>
                  </a-col>
                </a-row>
              </a-col>

            </a-row>
          </div>
        </a-card>
        <a-row style="margin-top:5px;">
          <a-col :xl="12">
            <a-card :loading="loading" title="项目巡检" style="height:295px;margin-right:5px;" headStyle="height:20px;line-height:20px;">
              项目巡查
            </a-card>
          </a-col>
          <a-col :xl="12">
            <a-card :loading="loading" title="项目成果" style="height:295px;margin-right:5px;" headStyle="height:20px;line-height:20px;">
              项目成果
            </a-card>
          </a-col>
        </a-row>

      </a-col>
    </a-row>

    
    
    <!--
    <a-card :loading="loading" :bordered="false" :body-style="{padding: '0'}">
      <div class="salesCard">
        <a-tabs default-active-key="1" size="large" :tab-bar-style="{marginBottom: '24px', paddingLeft: '16px'}">
          <div class="extra-wrapper" slot="tabBarExtraContent">
            <div class="extra-item">
              <a>今日</a>
              <a>本周</a>
              <a>本月</a>
              <a>本年</a>
            </div>
            <a-range-picker :style="{width: '256px'}" />
          </div>
          <a-tab-pane loading="true" tab="销售额" key="1">
            <a-row>
              <a-col :xl="16" :lg="12" :md="12" :sm="24" :xs="24">
                <bar title="销售额排行" :dataSource="barData"/>
              </a-col>
              <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
                <rank-list title="门店销售排行榜" :list="rankList"/>
              </a-col>
            </a-row>
          </a-tab-pane>
          <a-tab-pane tab="销售趋势" key="2">
            <a-row>
              <a-col :xl="16" :lg="12" :md="12" :sm="24" :xs="24">
                <bar title="销售额趋势" :dataSource="barData"/>
              </a-col>
              <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
                <rank-list title="门店销售排行榜" :list="rankList"/>
              </a-col>
            </a-row>
          </a-tab-pane>
        </a-tabs>
      </div>
    </a-card>

    <a-row>
      <a-col :span="24">
        <a-card :loading="loading" :bordered="false" title="最近一周访问量统计" :style="{ marginTop: '24px' }">
          <a-row>
            <a-col :span="6">
              <head-info title="今日IP" :content="loginfo.todayIp"></head-info>
            </a-col>
            <a-col :span="2">
              <a-spin class='circle-cust'>
                <a-icon slot="indicator" type="environment" style="font-size: 24px"  />
              </a-spin>
            </a-col>
            <a-col :span="6">
              <head-info title="今日访问" :content="loginfo.todayVisitCount"></head-info>
            </a-col>
            <a-col :span="2">
              <a-spin class='circle-cust'>
                <a-icon slot="indicator" type="team" style="font-size: 24px"  />
              </a-spin>
            </a-col>
            <a-col :span="6">
              <head-info title="总访问量" :content="loginfo.totalVisitCount"></head-info>
            </a-col>
            <a-col :span="2">
              <a-spin class='circle-cust'>
                <a-icon slot="indicator" type="rise" style="font-size: 24px"  />
              </a-spin>
            </a-col>
          </a-row>
          <line-chart-multid :fields="visitFields" :dataSource="visitInfo"></line-chart-multid>
        </a-card>
      </a-col>
    </a-row>

    -->
  </div>
</template>

<script>
import ChartCard from '@/components/ChartCard'
import ACol from 'ant-design-vue/es/grid/Col'
import ATooltip from 'ant-design-vue/es/tooltip/Tooltip'
import MiniArea from '@/components/chart/MiniArea'
import MiniBar from '@/components/chart/MiniBar'
import MiniProgress from '@/components/chart/MiniProgress'
import RankList from '@/components/chart/RankList'
import Bar from '@/components/chart/Bar'
import LineChartMultid from '@/components/chart/LineChartMultid'
import HeadInfo from '@/components/tools/HeadInfo.vue'

import Trend from '@/components/Trend'
import { getLoginfo, getVisitInfo } from '@/api/api'

const rankList = []
for (let i = 0; i < 7; i++) {
  rankList.push({
    name: '白鹭岛 ' + (i + 1) + ' 号店',
    total: 1234.56 - i * 100,
  })
}
const barData = []
for (let i = 0; i < 12; i += 1) {
  barData.push({
    x: `${i + 1}月`,
    y: Math.floor(Math.random() * 1000) + 200,
  })
}
export default {
  name: 'IndexChart',
  components: {
    ATooltip,
    ACol,
    ChartCard,
    MiniArea,
    MiniBar,
    MiniProgress,
    RankList,
    Bar,
    Trend,
    LineChartMultid,
    HeadInfo,
  },
  data() {
    return {
      managerImage: require('../../assets/manager.png'),
      loading: true,
      center: null,
      rankList,
      barData,
      loginfo: {},
      visitFields: ['ip', 'visit'],
      visitInfo: [],
      indicator: <a-icon type="loading" style="font-size: 24px" spin />,
    }
  },
  created() {
    setTimeout(() => {
      this.loading = !this.loading
    }, 1000)
    this.initLogInfo()
  },
  methods: {
    initLogInfo() {
      getLoginfo(null).then((res) => {
        if (res.success) {
          Object.keys(res.result).forEach((key) => {
            res.result[key] = res.result[key] + ''
          })
          this.loginfo = res.result
        }
      })
      getVisitInfo().then((res) => {
        if (res.success) {
          this.visitInfo = res.result
        }
      })
    },
  },
}
</script>

<style lang="less" scoped>
.circle-cust {
  position: relative;
  top: 28px;
  left: -100%;
}
.extra-wrapper {
  line-height: 55px;
  padding-right: 24px;

  .extra-item {
    display: inline-block;
    margin-right: 24px;

    a {
      margin-left: 24px;
    }
  }
}

/* 首页访问量统计 */
.head-info {
  position: relative;
  text-align: left;
  padding: 0 32px 0 0;
  min-width: 125px;

  &.center {
    text-align: center;
    padding: 0 32px;
  }

  span {
    color: rgba(0, 0, 0, 0.45);
    display: inline-block;
    font-size: 0.95rem;
    line-height: 42px;
    margin-bottom: 4px;
  }
  p {
    line-height: 42px;
    margin: 0;
    a {
      font-weight: 600;
      font-size: 1rem;
    }
  }
}

.project_row{
  margin-top:5px;border-bottom:1px solid #e1e1e1;
}

.project_left{
  width:80px;height:80px;background-color:#F06363;text-align:center;color:#ffffff;
}

.project_manager{
  width:80px;line-height:50px;font-size:20px;padding-top:20px;
}

.project_result{
  width:80px;line-height:30px;font-size:12px;
}

.project_name{
  height:20px;line-height:20px;font-weight:bold;color:#F06363;
}
.project_info{
  height:20px;line-height:20px;
}

.member_row{
  margin-top:5px;border:1px solid #e1e1e1;padding:5px;margin-right:5px;
}

.member_left{
  width:60px;height:80px;text-align:center;color:#ffffff;
}

.member_name{
  height:20px;line-height:20px;font-weight:bold;color:#F06363;
}
.member_info{
  height:20px;line-height:20px;
}


</style>