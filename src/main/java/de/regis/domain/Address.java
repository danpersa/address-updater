package de.regis.domain;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author danix
 */
@Entity
public final class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private final Long id;

    @Column(nullable = false)
    private final String street;

    @Column(nullable = false)
    private final String number;

    @Column(nullable = false)
    private final String zip;

    @Column(nullable = false)
    private final String city;

    @Column(nullable = false)
    private final Date created;

    @ManyToOne(optional = false)
    private final Company company;

    // this constructor should only be used by JPA (it's required by JPA)
    private Address() {
        id = null;
        street = null;
        zip = null;
        number = null;
        city = null;
        created = null;
        company = null;
    }

    public Address(@Nonnull String street, @Nonnull String number, @Nonnull String zip, String city, @Nonnull Company company) {
        this.id = null;
        this.street = checkNotNull(street);
        this.number = checkNotNull(number);
        this.zip = checkNotNull(zip);
        this.city = checkNotNull(city);
        this.created = new Date();
        this.company = checkNotNull(company);
    }

    @Nullable
    public Long getId() {
        return id;
    }

    @Nonnull
    public String getStreet() {
        return street;
    }

    @Nonnull
    public String getNumber() {
        return number;
    }

    @Nonnull
    public String getZip() {
        return zip;
    }

    @Nonnull
    public String getCity() {
        return city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, zip, city);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.street, other.street)
                && Objects.equals(this.number, other.number)
                && Objects.equals(this.zip, other.zip)
                && Objects.equals(this.city, other.city);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("id", id)
                .add("street", street)
                .add("number", number)
                .add("zip", zip)
                .add("city", city)
                .toString();
    }
}
