//[Mã câu hỏi (qCode): 7rpSgi5S].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
//Giao diện từ xa:
//public interface CharacterService extends Remote {
//public String requestCharacter(String studentCode, String qCode) throws RemoteException;
//public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
//}
//Trong đó:
//• Interface CharacterService được viết trong package RMI.
//• Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
//a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi văn bản đầu vào".
//b. Thực hiện thao tác đếm tần số xuất hiện của từng ký tự trong chuỗi đầu vào. Kết quả trả về là danh sách các ký tự kèm theo số lần xuất hiện của mỗi ký tự.
//Ví dụ: Chuỗi ban đầu "Hello world" -> Kết quả đếm tần số ký tự: {"H": 1, "e": 1, "l": 3, "o": 2, " ": 1, "w": 1, "r": 1, "d": 1}.
//c. Triệu gọi phương thức submitCharacter để gửi kết quả đếm tần số ký tự trở lại server dưới dạng chuỗi kết quả đã được định dạng.
//d. Kết thúc chương trình client.
import RMI.CharacterService;
import java.rmi.*;
import java.rmi.registry.*;

public class DemTanSuatKyTu2_KhoHon {
    public static void main(String[] args) throws Exception{
        String studentCode = "B17DCCN043", qCode = "7rpSgi5S" ;
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String a = sv.requestCharacter(studentCode, qCode);
        System.out.println(a);
        String kq = "{";
        int [] cnt = new int[256];
        // nói chung câu này phải tạo 2 vòng for nhé 
        for(int i = 0 ; i < a.length() ; i++){
            char c = a.charAt(i);
            cnt[c] ++;
            
        }
        
        for(int i = 0 ; i < a.length();i++){
            char c = a.charAt(i);
//            cnt[c] ++;
            if(cnt[c ] > 0 ){
                kq += ("\"" + c +"\"" + ": " + cnt[c] + ", ");
                cnt[c] = 0;
            }
        }
        kq  = kq.substring(0, kq.length() - 2);
        kq = kq + "}";
        System.out.println(kq);
        sv.submitCharacter(studentCode, qCode, kq);
    }
}
