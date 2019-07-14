package com.example.administrator.http.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/11.
 */

public class gsonAPP {

    /**
     * response : {"phoneNumber":{"detail":{"area":[{"city":"武汉"}],"province":"湖北","type":"domestic","operator":"移动"},"location":"湖北武汉移动"}}
     * responseHeader : {"status":200,"time":1533717512727,"version":"1.1.0"}
     */

    private ResponseBean response;
    private ResponseHeaderBean responseHeader;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public ResponseHeaderBean getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeaderBean responseHeader) {
        this.responseHeader = responseHeader;
    }

    public static class ResponseBean {
        /**
         * phoneNumber : {"detail":{"area":[{"city":"武汉"}],"province":"湖北","type":"domestic","operator":"移动"},"location":"湖北武汉移动"}
         */

        private PhoneNumberBean phoneNumber;

        public PhoneNumberBean getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(PhoneNumberBean phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public static class PhoneNumberBean {
            /**
             * detail : {"area":[{"city":"武汉"}],"province":"湖北","type":"domestic","operator":"移动"}
             * location : 湖北武汉移动
             */

            private DetailBean detail;
            private String location;

            public DetailBean getDetail() {
                return detail;
            }

            public void setDetail(DetailBean detail) {
                this.detail = detail;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public static class DetailBean {
                /**
                 * area : [{"city":"武汉"}]
                 * province : 湖北
                 * type : domestic
                 * operator : 移动
                 */

                private String province;
                private String type;
                private String operator;
                private List<AreaBean> area;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getOperator() {
                    return operator;
                }

                public void setOperator(String operator) {
                    this.operator = operator;
                }

                public List<AreaBean> getArea() {
                    return area;
                }

                public void setArea(List<AreaBean> area) {
                    this.area = area;
                }

                public static class AreaBean {
                    /**
                     * city : 武汉
                     */

                    private String city;

                    public String getCity() {
                        return city;
                    }

                    public void setCity(String city) {
                        this.city = city;
                    }
                }
            }
        }
    }

    public static class ResponseHeaderBean {
        /**
         * status : 200
         * time : 1533717512727
         * version : 1.1.0
         */

        private int status;
        private long time;
        private String version;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
