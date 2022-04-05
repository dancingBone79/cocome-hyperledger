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
import com.owlike.genson.Genson;

@Contract
public class ManageSupplierCRUDServiceImpl implements ManageSupplierCRUDService, Serializable, ContractInterface {
	private static final Genson genson = new Genson();
	
	
	public static Map<String, List<String>> opINVRelatedEntity = new HashMap<String, List<String>>();
	
	
	ThirdPartyServices services;
			
	public ManageSupplierCRUDServiceImpl() {
		services = new ThirdPartyServicesImpl();
	}

	
	//Shared variable from system services
	
	/* Shared variable from system services and get()/set() methods */
	private Object currentCashDeskPK;
	private CashDesk currentCashDesk;
	private Object currentStorePK;
	private Store currentStore;
			
	/* all get and set functions for temp property*/
	public CashDesk getCurrentCashDesk() {
		return EntityManager.getCashDeskByPK(getCurrentCashDeskPK());
	}

	private Object getCurrentCashDeskPK() {
		if (currentCashDeskPK == null)
			currentCashDeskPK = genson.deserialize(EntityManager.stub.getStringState("system.currentCashDeskPK"), Integer.class);

		return currentCashDeskPK;
	}	
	
	public void setCurrentCashDesk(CashDesk currentcashdesk) {
		if (currentcashdesk != null)
			setCurrentCashDeskPK(currentcashdesk.getPK());
		else
			setCurrentCashDeskPK(null);
		this.currentCashDesk = currentcashdesk;
	}

	private void setCurrentCashDeskPK(Object currentCashDeskPK) {
		String json = genson.serialize(currentCashDeskPK);
		EntityManager.stub.putStringState("system.currentCashDeskPK", json);
		//If we set currentCashDeskPK to null, the getter thinks this fields is not initialized, thus will read the old value from chain.
		if (currentCashDeskPK != null)
			this.currentCashDeskPK = currentCashDeskPK;
		else
			this.currentCashDeskPK = EntityManager.getGuid();
	}
	public Store getCurrentStore() {
		return EntityManager.getStoreByPK(getCurrentStorePK());
	}

	private Object getCurrentStorePK() {
		if (currentStorePK == null)
			currentStorePK = genson.deserialize(EntityManager.stub.getStringState("system.currentStorePK"), Integer.class);

		return currentStorePK;
	}	
	
	public void setCurrentStore(Store currentstore) {
		if (currentstore != null)
			setCurrentStorePK(currentstore.getPK());
		else
			setCurrentStorePK(null);
		this.currentStore = currentstore;
	}

	private void setCurrentStorePK(Object currentStorePK) {
		String json = genson.serialize(currentStorePK);
		EntityManager.stub.putStringState("system.currentStorePK", json);
		//If we set currentStorePK to null, the getter thinks this fields is not initialized, thus will read the old value from chain.
		if (currentStorePK != null)
			this.currentStorePK = currentStorePK;
		else
			this.currentStorePK = EntityManager.getGuid();
	}
				
	
	
	/* Generate inject for sharing temp variables between use cases in system service */
	
	
	/* Generate buiness logic according to functional requirement */
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean createSupplier(final Context ctx, int id, String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = createSupplier(id, name);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean createSupplier(int id, String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get supplier
		Supplier supplier = null;
		//no nested iterator --  iterator: any previous:any
		for (Supplier sup : (List<Supplier>)EntityManager.getAllInstancesOf(Supplier.class))
		{
			if (sup.getId() == id)
			{
				supplier = sup;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(supplier) == true) 
		{ 
			/* Logic here */
			Supplier sup = null;
			sup = (Supplier) EntityManager.createObject("Supplier");
			sup.setId(id);
			sup.setName(name);
			EntityManager.addObject("Supplier", sup);
			
			
			;
			// post-condition checking
			if (!(true && 
			sup.getId() == id
			 && 
			sup.getName() == name
			 && 
			StandardOPs.includes(((List<Supplier>)EntityManager.getAllInstancesOf(Supplier.class)), sup)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [name]
		//all relevant vars : sup
		//all relevant entities : Supplier
	} 
	 
	static {opINVRelatedEntity.put("createSupplier", Arrays.asList("Supplier"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public Supplier querySupplier(final Context ctx, int id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = querySupplier(id);
		return res;
	}

	@SuppressWarnings("unchecked")
	public Supplier querySupplier(int id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get supplier
		Supplier supplier = null;
		//no nested iterator --  iterator: any previous:any
		for (Supplier sup : (List<Supplier>)EntityManager.getAllInstancesOf(Supplier.class))
		{
			if (sup.getId() == id)
			{
				supplier = sup;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(supplier) == false) 
		{ 
			/* Logic here */
			
			
			;
			// post-condition checking
			if (!(true)) {
				throw new PostconditionException();
			}
			
			; return supplier;
		}
		else
		{
			throw new PreconditionException();
		}
	} 
	 
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean modifySupplier(final Context ctx, int id, String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = modifySupplier(id, name);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean modifySupplier(int id, String name) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get supplier
		Supplier supplier = null;
		//no nested iterator --  iterator: any previous:any
		for (Supplier sup : (List<Supplier>)EntityManager.getAllInstancesOf(Supplier.class))
		{
			if (sup.getId() == id)
			{
				supplier = sup;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(supplier) == false) 
		{ 
			/* Logic here */
			supplier.setId(id);
			supplier.setName(name);
			
			
			;
			// post-condition checking
			if (!(supplier.getId() == id
			 && 
			supplier.getName() == name
			 && 
			EntityManager.saveModified(Supplier.class)
			 &&
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//string parameters: [name]
		//all relevant vars : supplier
		//all relevant entities : Supplier
	} 
	 
	static {opINVRelatedEntity.put("modifySupplier", Arrays.asList("Supplier"));}
	
	
	@Transaction(intent = Transaction.TYPE.SUBMIT)
	public boolean deleteSupplier(final Context ctx, int id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		ChaincodeStub stub = ctx.getStub();
		EntityManager.setStub(stub);

		var res = deleteSupplier(id);
		return res;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteSupplier(int id) throws PreconditionException, PostconditionException, ThirdPartyServiceException {
		
		
		/* Code generated for contract definition */
		//Get supplier
		Supplier supplier = null;
		//no nested iterator --  iterator: any previous:any
		for (Supplier sup : (List<Supplier>)EntityManager.getAllInstancesOf(Supplier.class))
		{
			if (sup.getId() == id)
			{
				supplier = sup;
				break;
			}
				
			
		}
		/* previous state in post-condition*/

		/* check precondition */
		if (StandardOPs.oclIsundefined(supplier) == false && StandardOPs.includes(((List<Supplier>)EntityManager.getAllInstancesOf(Supplier.class)), supplier)) 
		{ 
			/* Logic here */
			EntityManager.deleteObject("Supplier", supplier);
			
			
			;
			// post-condition checking
			if (!(StandardOPs.excludes(((List<Supplier>)EntityManager.getAllInstancesOf(Supplier.class)), supplier)
			 && 
			true)) {
				throw new PostconditionException();
			}
			
		
			//return primitive type
			;				
			return true;
		}
		else
		{
			throw new PreconditionException();
		}
		//all relevant vars : supplier
		//all relevant entities : Supplier
	} 
	 
	static {opINVRelatedEntity.put("deleteSupplier", Arrays.asList("Supplier"));}
	
	
	
	
	/* temp property for controller */
			
	/* all get and set functions for temp property*/
	
	/* invarints checking*/
	public final static ArrayList<String> allInvariantCheckingFunction = new ArrayList<String>(Arrays.asList());
			
}
