//[Mã câu hỏi (qCode): qrt0AkbQ].  Một chương trình (tạm gọi là RMI server) cung cấp giao diện cho phép triệu gọi từ xa với thông tin như sau:
//-	Giao diện từ xa
//    public interface ObjectService extends Remote {
//        public Serializable requestObject(String studentCode, String qAlias) throws RemoteException;
//
//        public void submitObject(String studentCode, String qAlias, Serializable object) throws RemoteException;
//    }
//-	Lớp Product gồm các thông tin: id String, code String, importPrice double, exportPrice double.
//    Trường dữ liệu: private static final long serialVersionUID = 20151107L;
//    02 hàm khởi dựng 
//        public Product()
//        public Product(id String, String code,double ImportPrice, double ExportPrice)
//Trong đó:
//-	interface ObjectService và lớp Product được viết trong package RMI
//-	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService
//
//Yêu cầu yêu cầu viết chương trình tại máy trạm (RMI client) thực hiện chuẩn hóa sản phẩm theo thứ tự:
//a.	Triệu gọi phương thức requestObject để lấy về đối tượng sản phẩm cần chuẩn hóa.
//b.	Thực hiện chuẩn hóa đối tượng nhận được theo nguyên tắc:
//        - Chuyển mã sản phẩm thành in hoa.
//        - Cập nhật giá xuất (exportPrice) bằng giá nhập (importPrice) + 20%
//
//c.  Triệu gọi phương thức submitObject để gửi dữ liệu đã chuẩn hóa
//d.  Kết thúc chương trình client
import RMI.ObjectService;
import RMI.Product;
import java.rmi.*;
import java.rmi.registry.*;
public class CapNhatGiaXuatVaCodeSpProduct {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN366",qCode = "qrt0AkbQ";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Product a = (Product) sv.requestObject(studentCode, qCode);
        System.out.println(a);
        double im = a.getImportPrice();
        System.out.println(im);
////        // nhớ là làm tròn 2 chữ số sau số tp sau log nhé : cách này rủi ro quá
////        double moi = (double) Math.round(im *1.2 * 100) /100;
////        System.out.println(moi);
//        a.setExportPrice(moi);

        // làm tròn bằng cách này cho chuẩn 
        double moi = im* 1.2f;
        System.out.println(moi);
        String moicod = a.getCode().toUpperCase();
        a.setCode(moicod);
        sv.submitObject(studentCode, qCode, a);
    }
}
