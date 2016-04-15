package cn.figo.mydemo.danmaku;

/**
 * User: Ligx
 * Date: 2016-03-10
 * Time: 13:58
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class DanmakuBetBean {

    /**
     * cmd : BET_BETTOR
     * data : {"code":0,"msg":"ok","data":{"isInBet":false,"isBet":true,"gold":{"a":{"c":0,"progress":0},"b":{"c":0,"progress":0}},"silver":{"a":{"id":444368,"bet_id":20758,"uid":239862,"coin":12000,"coin_type":"silver","answer":"b","profit":0,"times":1,"amount":102,"total_amount":12000,"status":0,"update_time":"2016-03-10 13:57:17","c":10499689,"progress":41},"b":{"id":444311,"bet_id":20758,"uid":559833,"coin":10000,"coin_type":"silver","answer":"a","profit":0,"times":0.6,"amount":16666,"total_amount":16666,"status":0,"update_time":"2016-03-10 13:41:37","c":15115097,"progress":59}},"bet":{"id":20758,"uid":9617619,"question":"魔镜啊魔镜，告诉我今天谁会赢！","a":"李世石","b":"阿尔法狗","answer":"","status":0,"update_time":"2016-03-10 10:48:26"},"betStatus":true}}
     * roomid : 5440
     */

    private String cmd;
    /**
     * code : 0
     * msg : ok
     * data : {"isInBet":false,"isBet":true,"gold":{"a":{"c":0,"progress":0},"b":{"c":0,"progress":0}},"silver":{"a":{"id":444368,"bet_id":20758,"uid":239862,"coin":12000,"coin_type":"silver","answer":"b","profit":0,"times":1,"amount":102,"total_amount":12000,"status":0,"update_time":"2016-03-10 13:57:17","c":10499689,"progress":41},"b":{"id":444311,"bet_id":20758,"uid":559833,"coin":10000,"coin_type":"silver","answer":"a","profit":0,"times":0.6,"amount":16666,"total_amount":16666,"status":0,"update_time":"2016-03-10 13:41:37","c":15115097,"progress":59}},"bet":{"id":20758,"uid":9617619,"question":"魔镜啊魔镜，告诉我今天谁会赢！","a":"李世石","b":"阿尔法狗","answer":"","status":0,"update_time":"2016-03-10 10:48:26"},"betStatus":true}
     */

    private DataEntity data;
    private String roomid;

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getCmd() {
        return cmd;
    }

    public DataEntity getData() {
        return data;
    }

    public String getRoomid() {
        return roomid;
    }

    public static class DataEntity {
        private int code;
        private String msg;
        /**
         * isInBet : false
         * isBet : true
         * gold : {"a":{"c":0,"progress":0},"b":{"c":0,"progress":0}}
         * silver : {"a":{"id":444368,"bet_id":20758,"uid":239862,"coin":12000,"coin_type":"silver","answer":"b","profit":0,"times":1,"amount":102,"total_amount":12000,"status":0,"update_time":"2016-03-10 13:57:17","c":10499689,"progress":41},"b":{"id":444311,"bet_id":20758,"uid":559833,"coin":10000,"coin_type":"silver","answer":"a","profit":0,"times":0.6,"amount":16666,"total_amount":16666,"status":0,"update_time":"2016-03-10 13:41:37","c":15115097,"progress":59}}
         * bet : {"id":20758,"uid":9617619,"question":"魔镜啊魔镜，告诉我今天谁会赢！","a":"李世石","b":"阿尔法狗","answer":"","status":0,"update_time":"2016-03-10 10:48:26"}
         * betStatus : true
         */

        private DataEntity data;

        public void setCode(int code) {
            this.code = code;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setData(DataEntity data) {
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public DataEntity getData() {
            return data;
        }

        public static class DataBetEntity {
            private boolean isInBet;
            private boolean isBet;
            /**
             * a : {"c":0,"progress":0}
             * b : {"c":0,"progress":0}
             */

            private GoldEntity gold;
            /**
             * a : {"id":444368,"bet_id":20758,"uid":239862,"coin":12000,"coin_type":"silver","answer":"b","profit":0,"times":1,"amount":102,"total_amount":12000,"status":0,"update_time":"2016-03-10 13:57:17","c":10499689,"progress":41}
             * b : {"id":444311,"bet_id":20758,"uid":559833,"coin":10000,"coin_type":"silver","answer":"a","profit":0,"times":0.6,"amount":16666,"total_amount":16666,"status":0,"update_time":"2016-03-10 13:41:37","c":15115097,"progress":59}
             */

            private SilverEntity silver;
            /**
             * id : 20758
             * uid : 9617619
             * question : 魔镜啊魔镜，告诉我今天谁会赢！
             * a : 李世石
             * b : 阿尔法狗
             * answer :
             * status : 0
             * update_time : 2016-03-10 10:48:26
             */

            private BetEntity bet;
            private boolean betStatus;

            public void setIsInBet(boolean isInBet) {
                this.isInBet = isInBet;
            }

            public void setIsBet(boolean isBet) {
                this.isBet = isBet;
            }

            public void setGold(GoldEntity gold) {
                this.gold = gold;
            }

            public void setSilver(SilverEntity silver) {
                this.silver = silver;
            }

            public void setBet(BetEntity bet) {
                this.bet = bet;
            }

            public void setBetStatus(boolean betStatus) {
                this.betStatus = betStatus;
            }

            public boolean isIsInBet() {
                return isInBet;
            }

            public boolean isIsBet() {
                return isBet;
            }

            public GoldEntity getGold() {
                return gold;
            }

            public SilverEntity getSilver() {
                return silver;
            }

            public BetEntity getBet() {
                return bet;
            }

            public boolean isBetStatus() {
                return betStatus;
            }

            public static class GoldEntity {
                /**
                 * c : 0
                 * progress : 0
                 */

                private AEntity a;
                /**
                 * c : 0
                 * progress : 0
                 */

                private BEntity b;

                public void setA(AEntity a) {
                    this.a = a;
                }

                public void setB(BEntity b) {
                    this.b = b;
                }

                public AEntity getA() {
                    return a;
                }

                public BEntity getB() {
                    return b;
                }

                public static class AEntity {
                    private int c;
                    private int progress;

                    public void setC(int c) {
                        this.c = c;
                    }

                    public void setProgress(int progress) {
                        this.progress = progress;
                    }

                    public int getC() {
                        return c;
                    }

                    public int getProgress() {
                        return progress;
                    }
                }

                public static class BEntity {
                    private int c;
                    private int progress;

                    public void setC(int c) {
                        this.c = c;
                    }

                    public void setProgress(int progress) {
                        this.progress = progress;
                    }

                    public int getC() {
                        return c;
                    }

                    public int getProgress() {
                        return progress;
                    }
                }
            }

            public static class SilverEntity {
                /**
                 * id : 444368
                 * bet_id : 20758
                 * uid : 239862
                 * coin : 12000
                 * coin_type : silver
                 * answer : b
                 * profit : 0
                 * times : 1
                 * amount : 102
                 * total_amount : 12000
                 * status : 0
                 * update_time : 2016-03-10 13:57:17
                 * c : 10499689
                 * progress : 41
                 */

                private AEntity a;
                /**
                 * id : 444311
                 * bet_id : 20758
                 * uid : 559833
                 * coin : 10000
                 * coin_type : silver
                 * answer : a
                 * profit : 0
                 * times : 0.6
                 * amount : 16666
                 * total_amount : 16666
                 * status : 0
                 * update_time : 2016-03-10 13:41:37
                 * c : 15115097
                 * progress : 59
                 */

                private BEntity b;

                public void setA(AEntity a) {
                    this.a = a;
                }

                public void setB(BEntity b) {
                    this.b = b;
                }

                public AEntity getA() {
                    return a;
                }

                public BEntity getB() {
                    return b;
                }

                public static class AEntity {
                    private int id;
                    private int bet_id;
                    private int uid;
                    private int coin;
                    private String coin_type;
                    private String answer;
                    private int profit;
                    private int times;
                    private int amount;
                    private int total_amount;
                    private int status;
                    private String update_time;
                    private int c;
                    private int progress;

                    public void setId(int id) {
                        this.id = id;
                    }

                    public void setBet_id(int bet_id) {
                        this.bet_id = bet_id;
                    }

                    public void setUid(int uid) {
                        this.uid = uid;
                    }

                    public void setCoin(int coin) {
                        this.coin = coin;
                    }

                    public void setCoin_type(String coin_type) {
                        this.coin_type = coin_type;
                    }

                    public void setAnswer(String answer) {
                        this.answer = answer;
                    }

                    public void setProfit(int profit) {
                        this.profit = profit;
                    }

                    public void setTimes(int times) {
                        this.times = times;
                    }

                    public void setAmount(int amount) {
                        this.amount = amount;
                    }

                    public void setTotal_amount(int total_amount) {
                        this.total_amount = total_amount;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public void setUpdate_time(String update_time) {
                        this.update_time = update_time;
                    }

                    public void setC(int c) {
                        this.c = c;
                    }

                    public void setProgress(int progress) {
                        this.progress = progress;
                    }

                    public int getId() {
                        return id;
                    }

                    public int getBet_id() {
                        return bet_id;
                    }

                    public int getUid() {
                        return uid;
                    }

                    public int getCoin() {
                        return coin;
                    }

                    public String getCoin_type() {
                        return coin_type;
                    }

                    public String getAnswer() {
                        return answer;
                    }

                    public int getProfit() {
                        return profit;
                    }

                    public int getTimes() {
                        return times;
                    }

                    public int getAmount() {
                        return amount;
                    }

                    public int getTotal_amount() {
                        return total_amount;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public String getUpdate_time() {
                        return update_time;
                    }

                    public int getC() {
                        return c;
                    }

                    public int getProgress() {
                        return progress;
                    }
                }

                public static class BEntity {
                    private int id;
                    private int bet_id;
                    private int uid;
                    private int coin;
                    private String coin_type;
                    private String answer;
                    private int profit;
                    private double times;
                    private int amount;
                    private int total_amount;
                    private int status;
                    private String update_time;
                    private int c;
                    private int progress;

                    public void setId(int id) {
                        this.id = id;
                    }

                    public void setBet_id(int bet_id) {
                        this.bet_id = bet_id;
                    }

                    public void setUid(int uid) {
                        this.uid = uid;
                    }

                    public void setCoin(int coin) {
                        this.coin = coin;
                    }

                    public void setCoin_type(String coin_type) {
                        this.coin_type = coin_type;
                    }

                    public void setAnswer(String answer) {
                        this.answer = answer;
                    }

                    public void setProfit(int profit) {
                        this.profit = profit;
                    }

                    public void setTimes(double times) {
                        this.times = times;
                    }

                    public void setAmount(int amount) {
                        this.amount = amount;
                    }

                    public void setTotal_amount(int total_amount) {
                        this.total_amount = total_amount;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public void setUpdate_time(String update_time) {
                        this.update_time = update_time;
                    }

                    public void setC(int c) {
                        this.c = c;
                    }

                    public void setProgress(int progress) {
                        this.progress = progress;
                    }

                    public int getId() {
                        return id;
                    }

                    public int getBet_id() {
                        return bet_id;
                    }

                    public int getUid() {
                        return uid;
                    }

                    public int getCoin() {
                        return coin;
                    }

                    public String getCoin_type() {
                        return coin_type;
                    }

                    public String getAnswer() {
                        return answer;
                    }

                    public int getProfit() {
                        return profit;
                    }

                    public double getTimes() {
                        return times;
                    }

                    public int getAmount() {
                        return amount;
                    }

                    public int getTotal_amount() {
                        return total_amount;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public String getUpdate_time() {
                        return update_time;
                    }

                    public int getC() {
                        return c;
                    }

                    public int getProgress() {
                        return progress;
                    }
                }
            }

            public static class BetEntity {
                private int id;
                private int uid;
                private String question;
                private String a;
                private String b;
                private String answer;
                private int status;
                private String update_time;

                public void setId(int id) {
                    this.id = id;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public void setQuestion(String question) {
                    this.question = question;
                }

                public void setA(String a) {
                    this.a = a;
                }

                public void setB(String b) {
                    this.b = b;
                }

                public void setAnswer(String answer) {
                    this.answer = answer;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public void setUpdate_time(String update_time) {
                    this.update_time = update_time;
                }

                public int getId() {
                    return id;
                }

                public int getUid() {
                    return uid;
                }

                public String getQuestion() {
                    return question;
                }

                public String getA() {
                    return a;
                }

                public String getB() {
                    return b;
                }

                public String getAnswer() {
                    return answer;
                }

                public int getStatus() {
                    return status;
                }

                public String getUpdate_time() {
                    return update_time;
                }
            }
        }
    }
}
