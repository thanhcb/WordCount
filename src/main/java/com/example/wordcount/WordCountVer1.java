//package com.example.wordcount;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//
//import java.io.IOException;
//import java.util.StringTokenizer;
//
//public class WordCountVer1 {
//    public static class WordCountMapper extends Mapper<Object,Text,Text,IntWritable>{
//        @Override
//        protected void map(Object key, Text value, Context context){
//            Text text = new Text();
//            IntWritable out = new IntWritable(1);
//            StringTokenizer token = new StringTokenizer(value.toString());
//            while(token.hasMoreTokens()){
//                text.set(token.nextToken());
//                try {
//                    context.write(text,out);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    public static class WordCountReduce extends Reducer<Text,IntWritable,Text,IntWritable>{
//        @Override
//        protected void reduce(Text key, Iterable<IntWritable> values, Context context) {
//            int tong = 0;
//            IntWritable result = new IntWritable();
//            for(IntWritable sl:values){
//                tong +=sl.get();
//            }
//                result.set(tong);
//            try {
//                context.write(key,result);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            Configuration conf = new Configuration();
//            Job job = Job.getInstance(conf, "word count");
//            job.setJarByClass(WordCountVer1.class);
//            job.setJobName("WordCount");
//
//            job.setMapperClass(WordCountMapper.class);
//            job.setCombinerClass(WordCountReduce.class);
//            job.setReducerClass(WordCountReduce.class);
//
//            job.setOutputKeyClass(Text.class);
//            job.setOutputValueClass(IntWritable.class);
//
//            FileInputFormat.addInputPath(job,new Path("input"));
//            FileOutputFormat.setOutputPath(job,new Path("output"));
//            System.exit(job.waitForCompletion(true)?0:1);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
