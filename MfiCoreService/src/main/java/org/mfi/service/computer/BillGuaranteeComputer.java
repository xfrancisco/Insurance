package org.mfi.service.computer;

import java.util.List;

public class BillGuaranteeComputer {

	private BillLeaderEntity billLeaderEntity;
	private BillBrokerEntity billBrokerEntity;
	private List<BillCoinsurerEntity> billCoinsurerEntities;
	private List<BillPlacementEntity> billPlacementEntities;
	private BillAgencyEntity billAgencyEntity;

	public BillGuaranteeComputer(BillLeaderEntity billLeaderEntity, BillBrokerEntity billBrokerEntity,
			List<BillCoinsurerEntity> billCoinsurerEntities, List<BillPlacementEntity> billPlacementEntities, BillAgencyEntity billAgencyEntity) {
		this.billLeaderEntity = billLeaderEntity;
		this.billBrokerEntity = billBrokerEntity;
		this.billCoinsurerEntities = billCoinsurerEntities;
		this.billPlacementEntities = billPlacementEntities;
		this.billAgencyEntity = billAgencyEntity;
	}

	public void process() {

	}

	public BillLeaderEntity getBillLeaderEntity() {
		return billLeaderEntity;
	}

	public void setBillLeaderEntity(BillLeaderEntity billLeaderEntity) {
		this.billLeaderEntity = billLeaderEntity;
	}

	public BillBrokerEntity getBillBrokerEntity() {
		return billBrokerEntity;
	}

	public void setBillBrokerEntity(BillBrokerEntity billBrokerEntity) {
		this.billBrokerEntity = billBrokerEntity;
	}

	public BillAgencyEntity getBillAgencyEntity() {
		return billAgencyEntity;
	}

	public void setBillAgencyEntity(BillAgencyEntity billAgencyEntity) {
		this.billAgencyEntity = billAgencyEntity;
	}

	public List<BillCoinsurerEntity> getBillCoinsurerEntities() {
		return billCoinsurerEntities;
	}

	public void setBillCoinsurerEntities(List<BillCoinsurerEntity> billCoinsurerEntities) {
		this.billCoinsurerEntities = billCoinsurerEntities;
	}

	public List<BillPlacementEntity> getBillPlacementEntities() {
		return billPlacementEntities;
	}

	public void setBillPlacementEntities(List<BillPlacementEntity> billPlacementEntities) {
		this.billPlacementEntities = billPlacementEntities;
	}
}
