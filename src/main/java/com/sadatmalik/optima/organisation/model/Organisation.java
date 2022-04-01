package com.sadatmalik.optima.organisation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The organisation Jpa data entity class.
 *
 * @author sadatmalik
 */
@Getter
@Setter
@Entity
@Table(name = "organisations")
public class Organisation {

    @Id
    @Column(name = "organisation_id", nullable = false)
    String id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "contact_name", nullable = false)
    String contactName;

    @Column(name = "contact_email", nullable = false)
    String contactEmail;

    @Column(name = "contact_phone", nullable = false)
    String contactPhone;

}