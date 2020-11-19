package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="precoMin", nullable=false)
    private String precoMin;

    @Column(name="obs", nullable=true)
    private String obs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrecoMin() {
		return precoMin;
	}

	public void setPrecoMin(String precoMin) {
		this.precoMin = precoMin;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
    
    
}
