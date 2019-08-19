package vn.topica.itlab4.design_pattern.adapter_pattern;

public class MediaAdapter implements PlayMedia{
    private static final MediaAdapter instance = new MediaAdapter();
    private MediaAdapter (){

    }
    public static MediaAdapter getInstance(){
        return instance;
    }
    @Override
    public void playMedia(String name) {
        if (name.equals("mp3")) {
            Media media = new Mp3Play();
            media.playMp3();
        } else if (name.equals("mp4")) {
            Media media = new Mp4Play();
            media.playMp4();
        }
    }
}
