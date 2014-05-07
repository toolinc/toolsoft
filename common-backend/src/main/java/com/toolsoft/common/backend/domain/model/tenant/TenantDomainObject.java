// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.model.tenant;

import com.toolsoft.common.backend.domain.model.DomainObject;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;

import javax.persistence.DiscriminatorType;
import javax.persistence.MappedSuperclass;

/**
 * Specifies the behavior of all {@code Tenant} the entities.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "idTenant", discriminatorType = DiscriminatorType.INTEGER)
@MappedSuperclass
public abstract class TenantDomainObject extends DomainObject implements Tenant {
}
