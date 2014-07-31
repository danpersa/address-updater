package de.regis.domain;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author danix
 */
@Entity
public final class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private final Long id;

    @Column(nullable = false)
    private final String url;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private final Set<Address> addresses;

    // this constructor should only be used by JPA (it's required by JPA)
    private Company() {
        id = null;
        url = null;
        addresses = null;
    }

    public Company(@Nonnull String url) {
        this.url = checkNotNull(url);
        this.addresses = null;
        this.id = null;
    }

    @Nonnull
    public String getUrl() {
        return url;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    @Nonnull
    public Set<Address> getAddresses() {
        return addresses;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, url, addresses);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.url, other.url)
                && Objects.equals(this.addresses, other.addresses);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("id", id)
                .add("url", url)
                .add("addresses", addresses)
                .toString();
    }
}
