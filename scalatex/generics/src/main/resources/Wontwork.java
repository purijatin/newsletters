class Sample<T> extends CaptureType<T> {}

Sample<String> sample = new Sample<String>();
sample.getRawType(); //Will throw exception

