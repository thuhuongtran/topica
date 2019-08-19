package vn.topica.itlab4.onclass;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import vn.topica.itlab4.design_pattern.abstract_factory_pattern.FactoryProducer;
import vn.topica.itlab4.design_pattern.abstract_factory_pattern.TreeFactory;
import vn.topica.itlab4.design_pattern.adapter_pattern.MediaAdapter;
import vn.topica.itlab4.design_pattern.adapter_pattern.PlayMedia;
import vn.topica.itlab4.design_pattern.bridge_pattern.BridgePaint;
import vn.topica.itlab4.design_pattern.factory_pattern.Vehicle;
import vn.topica.itlab4.design_pattern.factory_pattern.VehicleFactory;
import vn.topica.itlab4.exercises.song_trainer.collection.Circle;
import vn.topica.itlab4.exercises.song_trainer.collection.CircleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExerciseOnClass {
    @Test
    public void testFactoryPattern() {
        VehicleFactory factory = VehicleFactory.getInstance();
        factory.getVehicle(Vehicle.Type.MOTOBIKE.name());
        factory.getVehicle(Vehicle.Type.BIKE.name());
        factory.getVehicle(Vehicle.Type.BYCYCLE.name());
    }

    @Test
    public void testAbstractFactoryPattern() {
        TreeFactory factory = FactoryProducer.growTree(false);
        factory.harvest("rose");
        factory = FactoryProducer.growTree(true);
        factory.harvest("apple");
    }

    @Test
    public void testAdapterPattern() {
        PlayMedia media = MediaAdapter.getInstance();
        media.playMedia("mp3");
        media.playMedia("mp4");
    }

    @Test
    public void testBridgePattern() {
        BridgePaint.getInstance().drawAndPaint();
    }

}
