package backEnd;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Wallet {
    /**
     * The RandomAccessFile of the wallet file
     */
    private RandomAccessFile file;
    private FileChannel fileChannel;
    private FileLock lock;


    /**
     * Creates a Wallet object
     * <p>
     * A Wallet object interfaces with the wallet RandomAccessFile
     */
    public Wallet() throws Exception {
        this.file = new RandomAccessFile(new File("backEnd/wallet.txt"), "rw");
        this.fileChannel = file.getChannel();


    }

    /**
     * Gets the wallet balance.
     *
     * @return The content of the wallet file as an integer
     */
    public int getBalance() throws IOException {

        this.file.seek(0);
        return Integer.parseInt(this.file.readLine());
        }
    /**
     * Sets a new balance in the wallet
     *
     * @param newBalance new balance to write in the wallet
     */
    public void setBalance(int newBalance) throws Exception {

            file.setLength(0);
            String str = Integer.valueOf(newBalance).toString() + '\n';
            file.writeBytes(str);
        }

    /**
     * Closes the RandomAccessFile in this.file
     */
    public void close() throws Exception {
        if (fileChannel != null) {
            fileChannel.close();
        }
        if (file != null) {
            file.close();
        }
    }

    public boolean safeWithdraw(int valueToWithdraw) throws Exception {

        FileLock lock = null;
        try {
            lock = fileChannel.lock();
            int currentBalance = this.getBalance();

            if (valueToWithdraw <= currentBalance) {
                Thread.sleep(3000);
                int newBalance = currentBalance - valueToWithdraw;
                this.setBalance(newBalance);
                return true;
            } else {
                return false;
            }
        }
            finally {
            if (lock != null) {
                lock.release();
            }
        }
    }
}
