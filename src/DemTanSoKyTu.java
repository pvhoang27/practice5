//[Mã câu hỏi (qCode): VmwXtwQt].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
//Giao diện từ xa:
//public interface CharacterService extends Remote {
//public String requestCharacter(String studentCode, String qCode) throws RemoteException;
//public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
//}
//Trong đó:
//•	Interface CharacterService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
//a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi đầu vào".
//b. Thực hiện đếm tần số xuất hiện của mỗi ký tự trong chuỗi đầu vào và tạo ra chuỗi kết quả theo định dạng <Ký tự><Số lần xuất hiện>, sắp xếp theo thứ tự xuất hiện của các ký tự trong chuỗi.
//Ví dụ: Chuỗi đầu vào "AAABBC" -> Kết quả: "A3B2C1".
//c. Triệu gọi phương thức submitCharacter để gửi chuỗi kết quả trở lại server.
//d. Kết thúc chương trình client.
import RMI.CharacterService;
import java.rmi.*;
import java.rmi.registry.*;
public class DemTanSoKyTu {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN699", qCode ="VmwXtwQt";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String a = sv.requestCharacter(studentCode, qCode);
        System.out.println(a);
        String kq = "";
        int [] cnt = new int[256];
        for(int i = 0 ; i < a.length(); i++){
            char c = a.charAt(i);
            cnt[c] ++;
        }
        
        for(int i = 0 ; i < a.length(); i++){
            char c = a.charAt(i);
            if(cnt[c] > 0){
                kq += (c + ""+ cnt[c]);
                cnt[c] = 0;
            }     
        }
        sv.submitCharacter(studentCode, qCode, kq);

    }
}
