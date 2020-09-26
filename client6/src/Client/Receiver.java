package Client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Receiver {

    public static void receive(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
        while (true) {
            try {
                socketChannel.read(byteBuffer);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Answer.toAnswer(objectInputStream.readObject());
                break;
            } catch (StreamCorruptedException e) {
                // ажидаем
            }
        }

    }
}
