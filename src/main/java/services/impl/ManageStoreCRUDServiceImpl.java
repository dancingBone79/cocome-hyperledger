package services.impl;

import services.*;
import entities.*;
import java.util.List;
import java.util.LinkedList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.Arrays;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import org.apache.commons.lang3.SerializationUtils;
import java.util.Iterator;
import org.hyperledger.fabric.shim.*;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.contract.*;

@Contract
public class ManageStoreCRUDServiceImpl implements ManageStoreCRUDService, Serializable, ContractInterface {
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public ManageStoreCRUDServiceImpl() {
		services = new ThirdPartyServicesImpl();
	}

	
	//Shared variable from system services
	
	/* Shared variable from system services and get()/set() methods */
	private CashDesk currentCashDesk;
	private Store currentStore;
			
	/* all get and set functions for temp property*/
	public CashDesk getCurrentCashDesk() {
		return currentCashDesk;
	}	
	
	public void setCurrentCashDesk(CashDesk currentcashdesk) {
		this.currentCashDesk = currentcashdesk;
	}
	public Store getCurrentStore() {
		return currentStore;
	}	
	
	public void setCurrentStore(Store currentstore) {
		this.currentStore = currentstore;
	}
				
	
	
	/* Generate inject for sharing temp variables between use cases in system service */
	public void refresh() {
		CoCoMESystem cocomesystem_service = (CoCoMESystem) ServiceManager.getAllInstancesOf(CoCoMESystem.class).get(0);
		cocomesystem_service.setCurrentCashDesk(currentCashDesk);
		cocomesystem_service.setCurrentStore(currentStore);
	}
	
	/* Generate buiness logic according to functional requirement */
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean createStore(final Context ctx, int id, String name, String address, boolean isopened) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = createStore(id, name, address, isopened);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean createStore(int id, String name, String address, boolean isopened) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get store
		Store store = null;
		//no nested iterator --  iterator: any previous:any
		for (Store sto : (List<Store>)EntityManager.getAllInstancesOf(Store.class))
		{
			if (sto.getId() == id)
			{
				store = sto;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(store) == true) 
		{ 
			/* Logic here */
			Store sto = null;
			sto = (Store) EntityManager.createObject("Store");
			sto.setId(id);
			sto.setName(name);
			sto.setAddress(address);
			sto.setIsOpened(isopened);
			EntityManager.addObject("Store", sto);
			
			
			refresh();
			// post-condition checking
			if (!(true && 
			sto.getId() == id
			 && 
			sto.getName() == name
			 && 
			sto.getAddress() == address
			 && 
			sto.getIsOpened() == isopened
			 && 
			StandardOPs.includes(((List<Store>)EntityManager.getAllInstancesOf(Store.class)), sto)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [name, address]
		//all relevant vars : sto
		//all relevant entities : Store
	} 
	 
	static {opINVRelatedEntity.put("createStore", Arrays.asList("Store"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Store queryStore(final Context ctx, int id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = queryStore(id);
		return res;
	}

	@SuppressWarnings("unchecked")
	public Store queryStore(int id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get store
		Store store = null;
		//no nested iterator --  iterator: any previous:any
		for (Store sto : (List<Store>)EntityManager.getAllInstancesOf(Store.class))
		{
			if (sto.getId() == id)
			{
				store = sto;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(store) == false) 
		{ 
			/* Logic here */
			
			
			refresh();
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			refresh(); return store;
		}
		else
		{
			throw new PreconditionException();
		}
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean modifyStore(final Context ctx, int id, String name, String address, boolean isopened) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = modifyStore(id, name, address, isopened);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean modifyStore(int id, String name, String address, boolean isopened) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get store
		Store store = null;
		//no nested iterator --  iterator: any previous:any
		for (Store sto : (List<Store>)EntityManager.getAllInstancesOf(Store.class))
		{
			if (sto.getId() == id)
			{
				store = sto;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(store) == false) 
		{ 
			/* Logic here */
			store.setId(id);
			store.setName(name);
			store.setAddress(address);
			store.setIsOpened(isopened);
			
			
			refresh();
			// post-condition checking
			if (!(store.getId() == id
			 && 
			store.getName() == name
			 && 
			store.getAddress() == address
			 && 
			store.getIsOpened() == isopened
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [name, address]
		//all relevant vars : store
		//all relevant entities : Store
	} 
	 
	static {opINVRelatedEntity.put("modifyStore", Arrays.asList("Store"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean deleteStore(final Context ctx, int id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = deleteStore(id);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteStore(int id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get store
		Store store = null;
		//no nested iterator --  iterator: any previous:any
		for (Store sto : (List<Store>)EntityManager.getAllInstancesOf(Store.class))
		{
			if (sto.getId() == id)
			{
				store = sto;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(store) == false && StandardOPs.includes(((List<Store>)EntityManager.getAllInstancesOf(Store.class)), store)) 
		{ 
			/* Logic here */
			EntityManager.deleteObject("Store", store);
			
			
			refresh();
			// post-condition checking
			if (!(StandardOPs.excludes(((List<Store>)EntityManager.getAllInstancesOf(Store.class)), store)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			refresh();				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//all relevant vars : store
		//all relevant entities : Store
	} 
	 
	static {opINVRelatedEntity.put("deleteStore", Arrays.asList("Store"));}
	
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
