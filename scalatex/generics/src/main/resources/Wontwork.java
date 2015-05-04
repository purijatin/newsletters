class Sample<T> extends CaptureType<T> {}

Sample<String> sample = new Sample<String>();
sample.getRawType(); //Will throw exception
//In this case, you Field.getGenericType() to optaing parametric type information

