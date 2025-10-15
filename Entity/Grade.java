/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASMJAVA2.Entity;

/**
 *
 * @author PC
 */
public class Grade {
   private String maSV;
   private String hoTen;
   private double tiengAnh;
   private double tinHoc;
   private double gdtc;

    public Grade() {
    }

    public Grade(String maSV, String hoTen, double tiengAnh, double tinHoc, double gdtc) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.tiengAnh = tiengAnh;
        this.tinHoc = tinHoc;
        this.gdtc = gdtc;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(double tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public double getTinHoc() {
        return tinHoc;
    }

    public void setTinHoc(double tinHoc) {
        this.tinHoc = tinHoc;
    }

    public double getGdtc() {
        return gdtc;
    }

    public void setGdtc(double gdtc) {
        this.gdtc = gdtc;
    }
   
    public double getDiemTB(){
        return (tiengAnh+tinHoc+gdtc)/3 ;
    }
    public Object[] toRow(){
        return new Object[]{
            maSV,hoTen,tiengAnh,tinHoc,gdtc,getDiemTB()
        };
    }
}
