package vn.topica.itlab4.design_pattern.adapter_pattern;

public class Mp4Play implements Media {
    @Override
    public void playMp3() {
        // do nothing
    }

    @Override
    public void playMp4() {
        System.out.println("Playing mp4");
    }
}
