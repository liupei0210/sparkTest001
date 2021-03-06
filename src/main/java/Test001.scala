import java.text.SimpleDateFormat
import java.util.Date


import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Test001 {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_COMMON_LIB_NATIVE_DIR", "/usr/hdp/2.6.3.0-235/hadoop/lib/native")
    System.setProperty("HADOOP_USER_NAME", "hdfs")
    val date = new Date()
    val sdf = new SimpleDateFormat("yyyyMMddHHmmss")
    val time = sdf.format(date)
    val conf = new SparkConf().setAppName("test001")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val testfile = sc.textFile("hdfs://172.18.130.100/liupei/test/fiction.txt")
/*        val str=testfile.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _).filter(_._2>10)
        .saveAsTextFile("hdfs://172.18.130.100/liupei/test/results/"+time)*/
    val str = testfile.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).filter(_._2 > 10).foreach(print(_))

/*    val spark = SparkSession.builder().appName("test001").master("local[*]").getOrCreate()
    val df=spark.read.text("hdfs://172.18.130.100/liupei/test/A.0016.0001.P001.201601.txt")
    df.schema*/
  }
}