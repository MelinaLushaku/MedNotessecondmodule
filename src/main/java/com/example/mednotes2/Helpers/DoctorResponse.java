package com.example.mednotes2.Helpers;

import java.io.Serializable;

public class DoctorResponse<T> implements Serializable {
    private T data;
    private String mesazhi;
    private int statusi;
    private String errori;

    public DoctorResponse(DoctorResponseBuilder doctorResponseBuilder){
        this.data=(T)doctorResponseBuilder.data;
        this.mesazhi=doctorResponseBuilder.mesazhi;
        this.statusi=doctorResponseBuilder.statusi;
        this.errori=doctorResponseBuilder.errori;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMesazhi() {
        return mesazhi;
    }

    public void setMesazhi(String mesazhi) {
        this.mesazhi = mesazhi;
    }

    public int getStatusi() {
        return statusi;
    }

    public void setStatusi(int statusi) {
        this.statusi = statusi;
    }

    public String getErrori() {
        return errori;
    }

    public void setErrori(String errori) {
        this.errori = errori;
    }

    public static class DoctorResponseBuilder<T>{
        private T data;
        private String mesazhi;
        private int statusi;
        private String errori;
        public DoctorResponseBuilder(int statusi){
            this.statusi = statusi;
        }
        public DoctorResponseBuilder setData(T data){
            this.data = data;
            return this;
        }
        public DoctorResponseBuilder setMesazhin(String mesazhi){
            this.mesazhi = mesazhi;
            return this;
        }
        public DoctorResponseBuilder setErrorin(String errori){
            this.errori = errori;
            return this;
        }
        public DoctorResponse build(){
            return new DoctorResponse (this);
        }

    }
}
