package com.example.kirupa.jsonnew;

import java.util.List;

public class ResponsePogo {

    private List<CountriesBean> countries;

    public List<CountriesBean> getCountries() {
        return countries;
    }

    public void setCountries(List<CountriesBean> countries) {
        this.countries = countries;
    }

    public static class CountriesBean {
        /**
         * countryname : India
         * flag : http://wptrafficanalyzer.in/p/demo1/india.png
         * language : Hindi
         * capital : New Delhi
         * currency : {"code":"INR","currencyname":"Rupee"}
         */

        private String countryname;
        private String flag;
        private String language;
        private String capital;
        private CurrencyBean currency;

        public String getCountryname() {
            return countryname;
        }

        public void setCountryname(String countryname) {
            this.countryname = countryname;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public CurrencyBean getCurrency() {
            return currency;
        }

        public void setCurrency(CurrencyBean currency) {
            this.currency = currency;
        }

        public static class CurrencyBean {
            /**
             * code : INR
             * currencyname : Rupee
             */

            private String code;
            private String currencyname;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCurrencyname() {
                return currencyname;
            }

            public void setCurrencyname(String currencyname) {
                this.currencyname = currencyname;
            }
        }
    }
}
