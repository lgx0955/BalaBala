package cn.figo.mydemo.bean;

import java.util.List;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 13:09
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LiveIndexBean extends ResponseBaseBean{

    private String message;
    private DataEntity data;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {

        private List<BannerEntity> banner;

        private List<EntranceIconsEntity> entranceIcons;

        private List<PartitionsEntity> partitions;

        public void setBanner(List<BannerEntity> banner) {
            this.banner = banner;
        }

        public void setEntranceIcons(List<EntranceIconsEntity> entranceIcons) {
            this.entranceIcons = entranceIcons;
        }

        public void setPartitions(List<PartitionsEntity> partitions) {
            this.partitions = partitions;
        }

        public List<BannerEntity> getBanner() {
            return banner;
        }

        public List<EntranceIconsEntity> getEntranceIcons() {
            return entranceIcons;
        }

        public List<PartitionsEntity> getPartitions() {
            return partitions;
        }

        public static class BannerEntity {
            private String title;
            private String img;
            private String remark;
            private String link;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public String getImg() {
                return img;
            }

            public String getRemark() {
                return remark;
            }

            public String getLink() {
                return link;
            }
        }

        public static class EntranceIconsEntity {
            private int id;
            private String name;

            public EntranceIconsEntity(int id, String name, EntranceIconEntity entrance_icon) {
                this.id = id;
                this.name = name;
                this.entrance_icon = entrance_icon;
            }

            private EntranceIconEntity entrance_icon;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setEntrance_icon(EntranceIconEntity entrance_icon) {
                this.entrance_icon = entrance_icon;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public EntranceIconEntity getEntrance_icon() {
                return entrance_icon;
            }

            public static class EntranceIconEntity {
                private String src;
                private String height;
                private String width;

                public void setSrc(String src) {
                    this.src = src;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getSrc() {
                    return src;
                }

                public String getHeight() {
                    return height;
                }

                public String getWidth() {
                    return width;
                }
            }
        }

        public static class PartitionsEntity {

            private PartitionEntity partition;

            private List<LivesEntity> lives;

            public void setPartition(PartitionEntity partition) {
                this.partition = partition;
            }

            public void setLives(List<LivesEntity> lives) {
                this.lives = lives;
            }

            public PartitionEntity getPartition() {
                return partition;
            }

            public List<LivesEntity> getLives() {
                return lives;
            }

            public static class PartitionEntity {
                private int id;
                private String name;
                private String area;
                private SubIconEntity sub_icon;
                private int count;

                public void setId(int id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public void setSub_icon(SubIconEntity sub_icon) {
                    this.sub_icon = sub_icon;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public int getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public String getArea() {
                    return area;
                }

                public SubIconEntity getSub_icon() {
                    return sub_icon;
                }

                public int getCount() {
                    return count;
                }

                public static class SubIconEntity {
                    private String src;
                    private String height;
                    private String width;

                    public void setSrc(String src) {
                        this.src = src;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }

                    public String getSrc() {
                        return src;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public String getWidth() {
                        return width;
                    }
                }
            }

            public static class LivesEntity {
                /**
                 * face : http://i0.hdslb.com/group1/M00/8D/6F/oYYBAFbeOWuAGJYdAAAyPSMQv8E496.jpg
                 * mid : 14837663
                 * name : 奈さま
                 */

                private OwnerEntity owner;
                /**
                 * src : http://x-img.hdslb.net/group1/M00/09/FB/oYYBAFZ83wiAaLTDAAEpmlJAPsQ571.jpg
                 * height : 180
                 * width : 320
                 */

                private CoverEntity cover;
                private String title;
                private int room_id;
                private int online;

                public void setOwner(OwnerEntity owner) {
                    this.owner = owner;
                }

                public void setCover(CoverEntity cover) {
                    this.cover = cover;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setRoom_id(int room_id) {
                    this.room_id = room_id;
                }

                public void setOnline(int online) {
                    this.online = online;
                }

                public OwnerEntity getOwner() {
                    return owner;
                }

                public CoverEntity getCover() {
                    return cover;
                }

                public String getTitle() {
                    return title;
                }

                public int getRoom_id() {
                    return room_id;
                }

                public int getOnline() {
                    return online;
                }

                public static class OwnerEntity {
                    private String face;
                    private int mid;
                    private String name;

                    public void setFace(String face) {
                        this.face = face;
                    }

                    public void setMid(int mid) {
                        this.mid = mid;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getFace() {
                        return face;
                    }

                    public int getMid() {
                        return mid;
                    }

                    public String getName() {
                        return name;
                    }
                }

                public static class CoverEntity {
                    private String src;
                    private int height;
                    private int width;

                    public void setSrc(String src) {
                        this.src = src;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public String getSrc() {
                        return src;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public int getWidth() {
                        return width;
                    }
                }
            }
        }
    }
}
