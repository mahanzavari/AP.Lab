import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HospitalSystem {
    private List<Coupon> coupons = new ArrayList<>();

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    public double applyCoupon(String code, double billAmount) {
        Iterator<Coupon> iterator = coupons.iterator();
        while (iterator.hasNext()) {
            Coupon coupon = iterator.next();
            if (coupon.getCode().equals(code) && !coupon.getExpirationDate().isBefore(LocalDate.now())) {
                return coupon.applyDiscount(billAmount);
            }
        }
        return billAmount; // No valid coupon found, return original amount
    }

    public static void main(String[] args) {
        HospitalSystem hospitalSystem = new HospitalSystem();
        
        // Adding some coupons
        hospitalSystem.addCoupon(new PercentageCoupon("ucs19d101", LocalDate.of(2024, 12, 31), "user123", 10));
        hospitalSystem.addCoupon(new FixedAmountCoupon("ucs19d102", LocalDate.of(2024, 12, 31), "user124", 50));
        
        // Applying a coupon
        double newBill = hospitalSystem.applyCoupon("ucs19d101", 500);
        System.out.println("Bill after applying coupon: " + newBill); // Should print 450.0 if 10% discount applied
    }
}