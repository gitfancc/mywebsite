package com.appscomm.sport.model;

public class StatisticsVO {
    private static final long serialVersionUID = -201309181506004140L;
    private int steps;
    private float dist;
    private float speed;
    private float cal;
    private int avgRate;
    private String endTime;

    /**
     * 获取步数
     * @return
     */
    public int getSteps(){
       return this.steps;
    }
    /**
     * 设置步数
     * @param Steps
     */
    public void setSteps(int Steps){
       this.steps=Steps;
    }
    /**
     * 获取距离
     * @return
     */
    public float getDist(){
       return this.dist;
    }
    /**
     * 设置距离
     * @param Dist
     */
    public void setDist(float Dist){
       this.dist=Dist;
    }
    /**
     * 获取速度
     * @return
     */
    public float getSpeed(){
       return this.speed;
    }
    /**
     * 设置速度
     * @param Speed
     */
    public void setSpeed(float Speed){
       this.speed=Speed;
    }
    /**
     * 获取卡路里
     * @return
     */
    public float getCal(){
       return this.cal;
    }
    /**
     * 设置卡路里
     * @param Cal
     */
    public void setCal(float Cal){
       this.cal=Cal;
    }

    /**
     * 获取平均心率
     * @return
     */
    public int getAvgrate(){
       return this.avgRate;
    }
    /**
     * 设置平均心率
     * @param Avgrate
     */
    public void setAvgRate(int Avgrate){
       this.avgRate=Avgrate;
    }
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
