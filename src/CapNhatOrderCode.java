//[Mã câu hỏi (qCode): YqzGvpCt].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để quản lý thông tin đơn hàng trong hệ thống thương mại điện tử. Chương trình sẽ ngẫu nhiên tạo ra đối tượng Order với các giá trị ban đầu và cung cấp cho RMI client như sau:
//    Giao diện từ xa:
//public interface ObjectService extends Remote {
//    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
//    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
//}
//Lớp Order gồm các thuộc tính: id String, customerCode String, orderDate String, shippingType String, orderCode String.
//•	Trường dữ liệu: private static final long serialVersionUID = 20241132L;
//•	02 hàm khởi dựng:
//    public Order()
//    public Order(String id, String customerCode, String orderDate, String shippingType)
//Trong đó:
//•	Interface ObjectService và lớp Order được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với đối tượng đơn hàng được nhận từ RMI Server:
//a. Triệu gọi phương thức requestObject để nhận đối tượng Order ngẫu nhiên từ server.
//b. Tạo mã orderCode cho đơn hàng dựa trên các quy tắc sau:
//•	Bắt đầu bằng hai ký tự đầu của shippingType, viết in hoa.
//•	Kế đến là ba ký tự cuối của customerCode.
//•	Cuối cùng là ngày và tháng từ orderDate (theo định dạng "ddMM").
//Ví dụ: Nếu đơn hàng có mã khách hàng là "C123456", ngày đặt hàng là "2023-10-05", và loại giao hàng là "Express", thì mã orderCode sẽ là "EX4560510".
//c. Cập nhật giá trị orderCode trong đối tượng Order.
//d. Triệu gọi phương thức submitObject để gửi đối tượng Order đã được xử lý trở lại server.
//e. Kết thúc chương trình client.

import RMI.ObjectService;
import RMI.Order;
import java.rmi.*;
import java.rmi.registry.*;
public class CapNhatOrderCode {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN120",qCode = "YqzGvpCt"; 
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Order a = (Order) sv.requestObject(studentCode, qCode);
        System.out.println(a);
        
        String dau = a.getShippingType().substring(0,2).toUpperCase();
        System.out.println(dau);
        String cusCode = a.getCustomerCode(); int n = cusCode.length();
        String giua = cusCode.substring(n-3);
        System.out.println(giua);
        String [] part = a.getOrderDate().split("-");
        String cuoi = part[2] + part[1];
        System.out.println(cuoi);
        String kq = dau+giua+cuoi;
        a.setOrderCode(kq);
        sv.submitObject(studentCode, qCode, a);
    }
}
