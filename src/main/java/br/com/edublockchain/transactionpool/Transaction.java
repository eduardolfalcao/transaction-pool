package br.com.edublockchain.transactionpool;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Transaction implements Comparable<Transaction>, Serializable{

	private static final long serialVersionUID = -8270876610064570814L;

	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	private String sender;
	private String receiver;
	private double amount, fee;
	
	private String uniqueID;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date creationTime;
	
	public Transaction(String sender, String receiver, double amount, double fee) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.fee = fee;;
		this.creationTime = new Date(System.currentTimeMillis());
		this.uniqueID = UUID.randomUUID().toString();
	}
	
	public Transaction(String sender, String receiver, double amount, double fee, Date creationTime) {
		this(sender, receiver, amount, fee);
		this.creationTime = creationTime;
	}
	
	@Override
	public String toString() {
		return "\nSender: "+sender+"; Receiver: "+receiver+"; Amount: "+amount+"; Fee: "+fee+"; Creation time: "+formatter.format(creationTime);
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	public String getUniqueID() {
		return uniqueID;
	}
	
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	@Override
	public int hashCode() {
		return uniqueID.hashCode();
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (Double.doubleToLongBits(fee) != Double.doubleToLongBits(other.fee))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (uniqueID == null) {
			if (other.uniqueID != null)
				return false;
		} else if (!uniqueID.equals(other.uniqueID))
			return false;
		return true;
	}

	@Override
	public int compareTo(Transaction t) {
		if(this.fee < t.getFee())
			return 1;
		else if(this.fee > t.getFee())
			return -1;
		else{
			//transactions are only equal if they have the same uniqueID
			if(uniqueID == t.getUniqueID())
				return 0;
			else
				return 1;
		}
	}
	
}
