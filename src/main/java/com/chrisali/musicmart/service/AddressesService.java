package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.AddressesDao;
import com.chrisali.musicmart.model.user.Address;
import com.chrisali.musicmart.model.user.User;

/**
 * Contains CRUD methods to manipulate {@link Address} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("addressesService")
public class AddressesService {
	
	@Autowired
	private AddressesDao addressesDao;
	
	/**
	 * Adds/updates {@link Address} object in/to database, depending on if object already exists in database
	 * 
	 * @param address
	 */
	public void createOrUpdate(Address address) {
		addressesDao.createOrUpdate(address);
	}
	
	/**
	 * @param id
	 * @return whether {@link Address} object exists in database
	 */
	public boolean exists(int id) {
		return addressesDao.exists(id);
	}
	
	/**
	 * @param id
	 * @return {@link Address} object for given id
	 */
	public Address getAddress(int id) {
		return addressesDao.getAddress(id);
	}
	
	/**
	 * @param usersId
	 * @param pageNumber
	 * @param resultsSize
	 * @return list of paginated {@link Address} objects resultsSize-objects long for a given {@link User} 
	 */
	@Secured("ROLE_USER")
	public List<Address> getPaginatedAddressesForUser(int usersId, int pageNumber, int resultsSize) {
		return addressesDao.getPaginatedAddressesForUser(usersId, pageNumber, resultsSize);
	}
	
	/**
	 * @param usersId
	 * @return total number of {@link Address} objects in database for a given {@link User}
	 */
	@Secured("ROLE_USER")
	public Long getTotalNumberOfAddressesForUser(int usersId) {
		return addressesDao.getTotalNumberOfAddressesForUser(usersId);
	}
	
	/**
	 * Deletes {@link Address} object from database
	 * @param id
	 */
	@Secured("ROLE_USER")
	public void delete(int id) {
		addressesDao.delete(id);
	}
}
