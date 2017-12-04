package DAO;

import beans.Company;
import beans.Coupon;

import java.util.Collection;

public interface CompanyDAO {
	public Company createCompany(Company company);
	public void removeCompany(Company company);
	public void updateCompany(Company company);
	public Company getCompany(long id);
	public Collection<Company> getAllCompanies();
	public Collection<Coupon> getCoupons(Company company);
	public Company login(String compName, String password); 
}
