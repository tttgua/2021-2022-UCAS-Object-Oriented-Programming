package model.building;


import constant.GameConstant;

/**
 * 医院
 */
public class Hospital extends Building
{

    public Hospital(int posX, int posY)
    {
        super(posX, posY);
        this.name = "医院";
    }

    public int getEvent()
    {
        return GameConstant.HOSPITAL_EVENT;
    } //当前地图并不可能发生
    public int passEvent()
    {
        return 0;
    }
}
