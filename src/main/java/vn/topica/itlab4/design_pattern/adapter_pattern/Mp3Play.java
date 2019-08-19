package vn.topica.itlab4.design_pattern.adapter_pattern;

public class Mp3Play implements Media {
    @Override
    public void playMp3() {
        System.out.println("Playing mp3.");
    }

    @Override
    public void playMp4() {
        // do nothing
    }
}
