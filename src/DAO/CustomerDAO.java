package DAO;

import beans.Coupon;
import beans.Customer;

import java.util.Collection;

public interface CustomerDAO {
	public Customer createCustomer(Customer customer);
	public void removeCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public Customer getCustomer(long id); 
	public Collection<Customer> getAllCustomer();
	public Collection<Coupon> getCoupons(Customer customer);
	public Customer Login(String customerName, String password);
}
