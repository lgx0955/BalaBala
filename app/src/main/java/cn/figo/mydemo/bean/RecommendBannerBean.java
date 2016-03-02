package cn.figo.mydemo.bean;

import java.util.List;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 00:28
 * Copyright (c)Ligx All rights reserved.
 */
public class RecommendBannerBean extends ResponseBaseBean{

    /**
     * code : 0
     * data : [{"title":"MCA2015","value":"http://www.bilibili.com/html/mca2015.html","image":"http://i0.hdslb.com/group1/M00/48/08/oYYBAFbRgTSATpPgAAFzM2dDa-0610.png","type":2,"weight":320,"remark":""},{"title":"daily","value":"http://www.bilibili.com/topic/v2/phone1082.html","image":"http://i0.hdslb.com/group1/M00/4F/03/oYYBAFbSc26AH8mPAAGemCri1GU935.jpg","type":2,"weight":201,"remark":""},{"title":"乖离性百万亚瑟王","value":"3","image":"http://i0.hdslb.com/group1/M00/42/F8/oYYBAFbRBz2AQX0EAACy2nOIPoU308.jpg","type":1,"weight":200,"remark":""},{"title":"pokemon","value":"http://www.bilibili.com/html/activity-pokemon.html","image":"http://i0.hdslb.com/group1/M00/35/D6/oYYBAFbOyoeAD-hBAAI9ojS8xTc456.jpg","type":2,"weight":150,"remark":""},{"title":"表情还原大作战","value":"http://www.bilibili.com/html/emotion.html","image":"http://i0.hdslb.com/group1/M00/43/EC/oYYBAFbRIhyAUivxAASaCXEAsE4535.png","type":2,"weight":120,"remark":""},{"title":"舞武器舞乱伎","value":"3128","image":"http://x-img.hdslb.net/group1/M00/A5/FF/oYYBAFaYuTuAYvqrAAHKkyrdrhw283.jpg","type":3,"weight":16,"remark":""}]
     */

    /**
     * title : MCA2015
     * value : http://www.bilibili.com/html/mca2015.html
     * image : http://i0.hdslb.com/group1/M00/48/08/oYYBAFbRgTSATpPgAAFzM2dDa-0610.png
     * type : 2
     * weight : 320
     * remark :
     */

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String title;
        private String value;
        private String image;
        private int type;
        private int weight;
        private String remark;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTitle() {
            return title;
        }

        public String getValue() {
            return value;
        }

        public String getImage() {
            return image;
        }

        public int getType() {
            return type;
        }

        public int getWeight() {
            return weight;
        }

        public String getRemark() {
            return remark;
        }
    }
}
