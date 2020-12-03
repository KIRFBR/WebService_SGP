package entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyService {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="client", nullable=false)
    private String client;

    @Column(name="service", nullable=false)
    private String service;

    @Column(name="qtdeParcela", nullable=true)
    private String qtdeParcela;

    @Column(name="vlrTotal", nullable=true)
    private String vlrTotal;

    @Column(name="dtVenc", nullable=true)
    private Date dtVenc;
    
    @Column(name="dtPgm", nullable=true)
    private Date dtPgm;
    
    @Column(name="obs", nullable=true)
    private String obs;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getQtdeParcela() {
		return qtdeParcela;
	}

	public void setQtdeParcela(String qtdeParcela) {
		this.qtdeParcela = qtdeParcela;
	}

	public String getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(String vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

	public Date getDtVenc() {
		return dtVenc;
	}

	public void setDtVenc(Date dtVenc) {
		this.dtVenc = dtVenc;
	}

	public Date getDtPgm() {
		return dtPgm;
	}

	public void setDtPgm(Date dtPgm) {
		this.dtPgm = dtPgm;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Long getId() {
		return id;
	}

  
    
}
