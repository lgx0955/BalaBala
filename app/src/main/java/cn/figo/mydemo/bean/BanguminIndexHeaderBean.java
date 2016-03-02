package cn.figo.mydemo.bean;

import java.util.List;

/**
 * User: Ligx
 * Date: 2016-03-02
 * Time: 11:18
 * Copyright (c)Ligx All rights reserved.
 */
public class BanguminIndexHeaderBean extends ResponseBaseBean{

    private String message;

    private ResultEntity result;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {

        private LatestUpdateEntity latestUpdate;

        private List<CategoriesEntity> categories;

        private List<RecommendCategoryEntity> recommendCategory;

        public void setLatestUpdate(LatestUpdateEntity latestUpdate) {
            this.latestUpdate = latestUpdate;
        }

        public void setCategories(List<CategoriesEntity> categories) {
            this.categories = categories;
        }

        public void setRecommendCategory(List<RecommendCategoryEntity> recommendCategory) {
            this.recommendCategory = recommendCategory;
        }

        public LatestUpdateEntity getLatestUpdate() {
            return latestUpdate;
        }

        public List<CategoriesEntity> getCategories() {
            return categories;
        }

        public List<RecommendCategoryEntity> getRecommendCategory() {
            return recommendCategory;
        }

        public static class LatestUpdateEntity {
            private String updateCount;
            /**
             * bangumi_title : 爱神巧克力
             * cover : http://i0.hdslb.com/group1/M00/60/AF/oYYBAFbVqE2ACkyCAADWfiZHmKs841.jpg
             * last_time : 1456886643
             * newest_ep_id : 84607
             * newest_ep_index : 13
             * season_id : 3160
             * title : 爱神巧克力
             * total_count : 15
             * watchingCount : 628
             */

            private List<ListEntity> list;

            public void setUpdateCount(String updateCount) {
                this.updateCount = updateCount;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public String getUpdateCount() {
                return updateCount;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public static class ListEntity {
                private String bangumi_title;
                private String cover;
                private String last_time;
                private String newest_ep_id;
                private String newest_ep_index;
                private String season_id;
                private String title;
                private String total_count;
                private String watchingCount;

                public void setBangumi_title(String bangumi_title) {
                    this.bangumi_title = bangumi_title;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public void setLast_time(String last_time) {
                    this.last_time = last_time;
                }

                public void setNewest_ep_id(String newest_ep_id) {
                    this.newest_ep_id = newest_ep_id;
                }

                public void setNewest_ep_index(String newest_ep_index) {
                    this.newest_ep_index = newest_ep_index;
                }

                public void setSeason_id(String season_id) {
                    this.season_id = season_id;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setTotal_count(String total_count) {
                    this.total_count = total_count;
                }

                public void setWatchingCount(String watchingCount) {
                    this.watchingCount = watchingCount;
                }

                public String getBangumi_title() {
                    return bangumi_title;
                }

                public String getCover() {
                    return cover;
                }

                public String getLast_time() {
                    return last_time;
                }

                public String getNewest_ep_id() {
                    return newest_ep_id;
                }

                public String getNewest_ep_index() {
                    return newest_ep_index;
                }

                public String getSeason_id() {
                    return season_id;
                }

                public String getTitle() {
                    return title;
                }

                public String getTotal_count() {
                    return total_count;
                }

                public String getWatchingCount() {
                    return watchingCount;
                }
            }
        }

        public static class CategoriesEntity {
            /**
             * cover : http://i2.hdslb.com/u_user/7056112bf5c62e89b29750aae08851df.jpg
             * orderType : 0
             * tag_id : 104
             * tag_name : 泪腺崩坏
             * type : 0
             */

            private CategoryEntity category;
            /**
             * count : 6
             * list : [{"bangumi_id":"528","bangumi_title":"我们仍未知道那天所看见的花的名字","cover":"http://i1.hdslb.com/sp/ce/ce73f2c8f5814fb11bade9c77949f1f7.jpg","is_finish":"1","new_ep":{"av_id":"1358913","cover":"http://i2.hdslb.com/video/3a/3a382e81ef246ee2805cdf631ca3b38f.jpg","danmaku":"2055956","episode_id":"15024","index":"11","index_title":"【合集】我们仍未知道那天所看见的花的名字","page":"11","up":{},"update_time":"2014-08-01 19:20:14.0"},"newest_ep_index":"11","season_id":"835","season_title":"我们仍未知道那天所看见的花的名字","spid":"607","title":"我们仍未知道那天所看见的花的名字","total_count":"11"},{"bangumi_id":"1830","bangumi_title":"穿越时空的少女","cover":"http://i1.hdslb.com/sp/16/16a6fa3bbc6470e113c4616509c57dde.jpg","is_finish":"1","new_ep":{"av_id":"484754","cover":"http://i0.hdslb.com/u_f/e4c819adcb23ab7cfa75e26537c822f3.jpg","danmaku":"730261","episode_id":"65786","index":"1","index_title":"【剧场版】穿越时空的少女【720P】","page":"1","up":{},"update_time":"2013-02-24 00:20:26.0"},"newest_ep_index":"1","season_id":"2687","season_title":"穿越时空的少女","spid":"44575","title":"穿越时空的少女","total_count":"1"},{"bangumi_id":"1140","bangumi_title":"四月是你的谎言","cover":"http://i1.hdslb.com/sp/61/617b3424a2a675e140c1e7ebe6c0b2ac.jpg","is_finish":"1","new_ep":{"av_id":"2125251","cover":"http://i0.hdslb.com/video/0e/0e2a0783f35af6569d615085b4e58ba2.jpg","danmaku":"3386439","episode_id":"80041","index":"22","index_title":"【10月/完结】四月是你的谎言 22【bilibili正版】","page":"1","up":{},"update_time":"2015-03-20 01:45:22.0"},"newest_ep_index":"22","season_id":"1699","season_title":"四月是你的谎言","spid":"28711","title":"四月是你的谎言","total_count":"22"},{"bangumi_id":"1210","bangumi_title":"AIR","cover":"http://i2.hdslb.com/sp/2f/2f52a19704e373d861600c345f8333c8.jpg","is_finish":"1","new_ep":{"av_id":"106274","cover":"http://i1.hdslb.com/u_user/d1cb62c00578a5bb5042a32464a9c1eb.jpg","danmaku":"2927723","episode_id":"36866","index":"15","index_title":"AIR in SUMMER（后篇） あめつち～universe～ （天地～universe～）","page":"15","up":{},"update_time":"2011-06-22 08:57:56.0"},"newest_ep_index":"15","season_id":"1816","season_title":"AIR","spid":"2383","title":"AIR","total_count":"12"},{"bangumi_id":"773","bangumi_title":"CLANNAD","cover":"http://i1.hdslb.com/sp/2f/2f44c9695b3bec3eb264b4e6364640b9.jpg","is_finish":"1","new_ep":{"av_id":"13441","cover":"http://i1.hdslb.com/u_user/9de2a763ec16de18f183db4bdd96184e.jpg","danmaku":"2471565","episode_id":"21458","index":"25","index_title":"CLANNAD~After Story~【1-25话全】【澄空】BD1080P","page":"25","up":{},"update_time":"2010-07-13 08:16:14.0"},"newest_ep_index":"25","season_id":"1178","season_title":"第二季","spid":"17602","title":"CLANNAD 第二季","total_count":"25"},{"bangumi_id":"773","bangumi_title":"CLANNAD","cover":"http://i0.hdslb.com/sp/18/18f90271d2ba5455e80c0f919c20da1b.jpg","is_finish":"1","new_ep":{"av_id":"102279","cover":"http://i1.hdslb.com/u_user/81f2195c21aaeab2d4417b0384b6c892.jpg","danmaku":"2747950","episode_id":"34512","index":"SP2","index_title":"特别篇：もう一つの世界 智代编（另一个世界 智代篇）","page":"24","up":{},"update_time":"2011-06-11 11:24:22.0"},"newest_ep_index":"SP2","season_id":"1177","season_title":"第一季","spid":"799","title":"CLANNAD 第一季","total_count":"22"}]
             * pages : 1
             */

            private ListEntity list;

            public void setCategory(CategoryEntity category) {
                this.category = category;
            }

            public void setList(ListEntity list) {
                this.list = list;
            }

            public CategoryEntity getCategory() {
                return category;
            }

            public ListEntity getList() {
                return list;
            }

            public static class CategoryEntity {
                private String cover;
                private int orderType;
                private String tag_id;
                private String tag_name;
                private String type;

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public void setOrderType(int orderType) {
                    this.orderType = orderType;
                }

                public void setTag_id(String tag_id) {
                    this.tag_id = tag_id;
                }

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getCover() {
                    return cover;
                }

                public int getOrderType() {
                    return orderType;
                }

                public String getTag_id() {
                    return tag_id;
                }

                public String getTag_name() {
                    return tag_name;
                }

                public String getType() {
                    return type;
                }
            }

            public static class ListEntity {
                private String count;
                private String pages;
                /**
                 * bangumi_id : 528
                 * bangumi_title : 我们仍未知道那天所看见的花的名字
                 * cover : http://i1.hdslb.com/sp/ce/ce73f2c8f5814fb11bade9c77949f1f7.jpg
                 * is_finish : 1
                 * new_ep : {"av_id":"1358913","cover":"http://i2.hdslb.com/video/3a/3a382e81ef246ee2805cdf631ca3b38f.jpg","danmaku":"2055956","episode_id":"15024","index":"11","index_title":"【合集】我们仍未知道那天所看见的花的名字","page":"11","up":{},"update_time":"2014-08-01 19:20:14.0"}
                 * newest_ep_index : 11
                 * season_id : 835
                 * season_title : 我们仍未知道那天所看见的花的名字
                 * spid : 607
                 * title : 我们仍未知道那天所看见的花的名字
                 * total_count : 11
                 */

                private List<ListEntity> list;

                public void setCount(String count) {
                    this.count = count;
                }

                public void setPages(String pages) {
                    this.pages = pages;
                }

                public void setList(List<ListEntity> list) {
                    this.list = list;
                }

                public String getCount() {
                    return count;
                }

                public String getPages() {
                    return pages;
                }

                public List<ListEntity> getList() {
                    return list;
                }

            }
        }

        public static class RecommendCategoryEntity {
            private String cover;
            private String tag_id;
            private String tag_name;

            public void setCover(String cover) {
                this.cover = cover;
            }

            public void setTag_id(String tag_id) {
                this.tag_id = tag_id;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public String getCover() {
                return cover;
            }

            public String getTag_id() {
                return tag_id;
            }

            public String getTag_name() {
                return tag_name;
            }
        }
    }
}
