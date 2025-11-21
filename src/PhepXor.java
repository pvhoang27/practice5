//[Mã câu hỏi (qCode): ULsd1Y63].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
//Giao diện từ xa:
//public interface ByteService extends Remote {
//public byte[] requestData(String studentCode, String qCode) throws RemoteException;
//public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
//}
//Trong đó:
//•	Interface ByteService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
//a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server, đại diện cho một chuỗi văn bản ASCII.
//b. Thực hiện mã hóa XOR cho mảng dữ liệu nhị phân bằng cách sử dụng một khóa là chuỗi "PTIT". Thực hiện phép XOR trên từng byte trong mảng dữ liệu với byte tương ứng trong khóa (khóa sẽ được lặp lại để khớp với độ dài của mảng dữ liệu).
//Ví dụ: Nếu dữ liệu nhị phân nhận được là [72, 101, 108, 108, 111] (tương ứng với chuỗi "Hello"), và khóa là "PTIT", chương trình sẽ thực hiện mã hóa như sau:
//•	Chuyển khóa "PTIT" thành mảng byte [80, 84, 73, 84].
//•	Lặp lại khóa để khớp độ dài dữ liệu: [80, 84, 73, 84, 80].
//•	Thực hiện phép XOR trên từng byte của dữ liệu với khóa:
//•	Kết quả mã hóa là mảng [24, 49, 37, 56, 63].
//c. Triệu gọi phương thức submitData để gửi mảng dữ liệu đã được mã hóa bằng XOR trở lại server.
//d. Kết thúc chương trình client.


import RMI.ByteService;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
public class PhepXor {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN014", qCode = "ULsd1Y63";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ByteService sv =(ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData(studentCode, qCode);
        System.out.println(a);
        // nếu muốn in ra để nhìn
        // 1 là kiểu String
        String aa = new String(a);
        System.out.println(aa);
        //2 là kiểu list
        String k = Arrays.toString(a);
        System.out.println(k);
        byte[] kq = new byte[a.length];
        byte[] keyGoc = "PTIT".getBytes();
        byte[] keyMoi = new  byte[a.length];
        // vì là key gốc bị thọt 1 kí tự nên cần lặp lại để nó = kí tự thì mới xor dc 
        for(int i = 0 ; i < a.length; i++){
            keyMoi[i] =keyGoc[i % keyGoc.length];
        }
        String moi = new String(keyMoi);
        System.out.println(moi);
        //xor thôi
        for(int i = 0 ; i < a.length; i++){
            kq[i] = (byte) (a[i] ^ keyMoi[i]);
        }
        sv.submitData(studentCode, qCode, kq);
        
    }
}
