package org.rui.io.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * 对象 序列化 以下这些操作都使得事情变得更加复杂，加大了难度，然而，真正的序列化过程却是非常简单的
 * 
 * @author lenovo
 * 
 */

class Data implements Serializable {
	private int n;

	public Data(int n) {
		this.n = n;
	}

	public String toString() {
		return Integer.toString(n);
	}
}

public class Worm implements Serializable {
	private static Random rand = new Random(47);
	private Data[] d = { new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10)), new Data(rand.nextInt(10)) };

	private Worm next;
	private char c;

	// value of i==number of segments
	public Worm(int i, char x) {
		System.out.println("worm constructor;" + i);
		c = x;
		if (--i > 0)
			next = new Worm(i, (char) (x + 1));
	}

	//
	public Worm() {
		System.out.println("defalult constructor");
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(c);
		result.append("(");
		for (Data data : d)
			result.append(data);
		result.append(")");
		if (next != null)
			result.append(next);
		return result.toString();
	}

	// /////////////////////////////////////////
	public static void main(String[] args) throws Exception {
		Worm w = new Worm(6, 'a');
		System.out.println(" w:" + w);
		// 序列化到
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"worm.out"));
		out.writeObject("Worm storage\n");
		out.writeObject(w);
		out.close();
		// 读取
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"worm.out"));
		String s = (String) in.readObject();
		Worm worm = (Worm) in.readObject();
		System.out.println("s:" + s + " w2:" + worm);
		// ////////////////////////////////////////////////////////
		// 再次写入
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject("Worm storage\n");
		oos.writeObject(w);
		oos.flush();

		// 读取
		ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(
				bos.toByteArray()));
		s = (String) in2.readObject();
		Worm w3 = (Worm) in2.readObject();
		System.out.println("s:" + s + " w3:" + w3);
	}

}
/**
 * output: worm constructor;6 worm constructor;5 worm constructor;4 worm
 * constructor;3 worm constructor;2 worm constructor;1
 * w:a(853)b(119)c(802)d(788)e(199)f(881) s:Worm storage
 * w2:a(853)b(119)c(802)d(788)e(199)f(881) s:Worm storage
 * w3:a(853)b(119)c(802)d(788)e(199)f(881)
 */

/**
 * doc Java 1.1 增添了一种有趣的特性，名为“对象序列化”（Object Serialization）。它面向那些实现了 Serializable
 * 接口的对象，可将它们转换成一系列字节，并可在以后完全恢复回原来的样子。这一过程亦可
 * 通过网络进行。这意味着序列化机制能自动补偿操作系统间的差异。换句话说，可以先在Windows 机器上创
 * 建一个对象，对其序列化，然后通过网络发给一台Unix 机器，然后在那里准确无误地重新“装配”。不必关
 * 心数据在不同机器上如何表示，也不必关心字节的顺序或者其他任何细节。
 * 就其本身来说，对象的序列化是非常有趣的，因为利用它可以实现“有限持久化”。请记住“持久化”意味
 * 着对象的“生存时间”并不取决于程序是否正在执行——它存在或“生存”于程序的每一次调用之间。通过
 * 序列化一个对象，将其写入磁盘，以后在程序重新调用时重新恢复那个对象，就能圆满实现一种“持久”效
 * 果。之所以称其为“有限”，是因为不能用某种“persistent”（持久）关键字简单地地定义一个对象，并
 * 让系统自动照看其他所有细节问题（尽管将来可能成为现实）。相反，必须在自己的程序中明确地序列化和 组装对象。
 * 语言里增加了对象序列化的概念后，可提供对两种主要特性的支持。Java 1.1 的“远程方法调用”（RMI）
 * 
 * 使本来存在于其他机器的对象可以表现出好象就在本地机器上的行为。将消息发给远程对象时，需要通过对 象序列化来传输参数和返回值。RMI 将在第15
 * 章作具体讨论。 对象的序列化也是Java Beans 必需的，后者由Java 1.1 引入。使用一个Bean 时，它的状态信息通常在设计
 * 期间配置好。程序启动以后，这种状态信息必须保存下来，以便程序启动以后恢复；具体工作由对象序列化 完成。
 * 对象的序列化处理非常简单，只需对象实现了Serializable 接口即可（该接口仅是一个标记，没有方法）。 在Java 1.1
 * 中，许多标准库类都发生了改变，以便能够序列化——其中包括用于基本数据类型的全部封装 器、所有集合类以及其他许多东西。甚至Class
 * 对象也可以序列化（第11 章讲述了具体实现过程）。 为序列化一个对象，首先要创建某些OutputStream
 * 对象，然后将其封装到ObjectOutputStream 对象内。此
 * 时，只需调用writeObject()即可完成对象的序列化，并将其发送给OutputStream。相反的过程是将一个 InputStream
 * 封装到ObjectInputStream 内，然后调用readObject()。和往常一样，我们最后获得的是指向 一个上溯造型Object
 * 的句柄，所以必须下溯造型，以便能够直接设置。 对象序列化特别“聪明”的一个地方是它不仅保存了对象的“全景图”，而且能追踪对象内包含的所有句柄
 * 并保存那些对象；接着又能对每个对象内包含的句柄进行追踪；以此类推。我们有时将这种情况称为“对象
 * 网”，单个对象可与之建立连接。而且它还包含了对象的句柄数组以及成员对象。若必须自行操纵一套对象
 * 序列化机制，那么在代码里追踪所有这些链接时可能会显得非常麻烦。在另一方面，由于Java 对象的序列化
 * 似乎找不出什么缺点，所以请尽量不要自己动手，让它用优化的算法自动维护整个对象网。下面这个例子对
 * 序列化机制进行了测试。它建立了许多链接对象的一个“Worm”（蠕虫），每个对象都与Worm 中的下一段链
 * 接，同时又与属于不同类（Data）的对象句柄数组链接：
 * 
 * 更有趣的是，Worm 内的Data 对象数组是用随机数字初始化的（这样便不用怀疑编译器保留了某种原始信 息）。每个Worm 段都用一个Char
 * 标记。这个Char 是在重复生成链接的Worm 列表时自动产生的。创建一个 Worm
 * 时，需告诉构建器希望它有多长。为产生下一个句柄（next），它总是用减去1 的长度来调用Worm 构 318 建器。最后一个next
 * 句柄则保持为null（空），表示已抵达Worm 的尾部。 上面的所有操作都是为了加深事情的复杂程度，加大对象序列化的难度。然而，真正的序列化过程却是非常
 * 简单的。一旦从另外某个流里创建了ObjectOutputStream，writeObject()就会序列化对象。注意也可以为 一个String
 * 调用writeObject()。亦可使用与DataOutputStream 相同的方法写入所有基本数据类型（它们 有相同的接口）。 有两个单独的try
 * 块看起来是类似的。第一个读写的是文件，而另一个读写的是一个ByteArray（字节数 组）。可利用对任何DataInputStream
 * 或者DataOutputStream 的序列化来读写特定的对象；正如在关于连网 的那一章会讲到的那样，这些对象甚至包括网络
 * 
 * 可以看出，装配回原状的对象确实包含了原来那个对象里包含的所有链接。
 * 注意在对一个Serializable（可序列化）对象进行重新装配的过程中，不会调用任何构建器（甚至默认构建 器）。整个对象都是通过从InputStream
 * 中取得数据恢复的。 作为Java 1.1 特性的一种，我们注意到对象的序列化并不属于新的Reader 和Writer 层次结构的一部分，而
 * 是沿用老式的InputStream 和OutputStream 结构。所以在一些特殊的场合下，不得不混合使用两种类型的层 次结构。
 */
