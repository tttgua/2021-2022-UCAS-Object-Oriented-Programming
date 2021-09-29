import java.util.*;
class FactoryCar
{
    int ID;
    String color;
    String car_type;
    int price;
    Date produce_date;
    int life_span;
    boolean isautomatic;
    boolean issold;

    int getSalesVolume(boolean issold)
    {
        this.issold = issold;
        if (issold)
            return this.price;
        else
            return 0;
    }

    void sell()
    {
        this.issold = true;
    }

    void format()
    {
        this.ID = 0;
        this.color = "init";
        this.car_type = "init";
        this.price = 0;
        this.produce_date = 0;
        this.life_span = 0;
        this.isautomatic = false;
        this.issold = false;
    }
}

class OwnerCar
{
    int ID;
    String color;
    String car_type;
    String gasoline_type;
    int price;
    int max_passenger;
    int cur_passenger;
    int max_speed;
    int cur_speed;
    int cur_gasoline;
    Date purchase_date;
    int mileage;
    boolean isautomatic;
    boolean isrunning;
    boolean is_windows_open[] = new boolean[4];

    void add_gasoline(int oil_quantity)
    {
        this.cur_gasoline += oil_quantity;
    }

    int getSeatRemain()
    {
        return max_passenger - cur_passenger;
    }

    void ignite()
    {
        this.isrunning = true;
    }

    void stall()
    {
        this.isrunning = false;
    }

    void OpenWindows(int windowsID)
    {
        if (windowsID >= 0 && windowsID <= 3)
            this.is_windows_open[windowsID] = true;
    }

    void CloseWindows(int windowsID)
    {
        if (windowsID >= 0 && windowsID <= 3)
            this.is_windows_open[windowsID] = false;
    }
}

class TrafficManagerCar
{
    int ID;
    String color;
    String car_type;
    String car_owner;
    boolean isviolate;
    int fines;

    int getFineNum()
    {
        if (this.isviolate)
            return this.fines;
        else
            return 0;
    }

    String getOwner()
    {
        return this.car_owner;
    }

    String getType()
    {
        return this.car_type;
    }
}