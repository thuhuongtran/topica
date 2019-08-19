package vn.topica.itlab4.design_pattern.factory_pattern;

public class VehicleFactory {
    private static VehicleFactory instance = new VehicleFactory();
    private VehicleFactory(){}

    public static VehicleFactory getInstance() {
        return instance;
    }

    public void getVehicle(String vehicleName) {
        if (vehicleName.equals(Vehicle.Type.MOTOBIKE.name())) {
            System.out.println((new Motobike()).run());
        } else if (vehicleName.equals(Vehicle.Type.BIKE.name())) {
            System.out.println((new Bike()).run());
        } else if (vehicleName.equals(Vehicle.Type.BYCYCLE.name())) {
            System.out.println((new Bicycle()).run());
        }
    }
}
