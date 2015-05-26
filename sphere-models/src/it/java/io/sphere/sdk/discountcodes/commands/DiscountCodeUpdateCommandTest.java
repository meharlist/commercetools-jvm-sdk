package io.sphere.sdk.discountcodes.commands;

import io.sphere.sdk.cartdiscounts.CartDiscount;
import io.sphere.sdk.cartdiscounts.CartDiscountFixtures;
import io.sphere.sdk.cartdiscounts.CartPredicate;
import io.sphere.sdk.cartdiscounts.commands.CartDiscountUpdateCommand;
import io.sphere.sdk.discountcodes.DiscountCode;
import io.sphere.sdk.discountcodes.commands.updateactions.*;
import io.sphere.sdk.models.LocalizedStrings;
import io.sphere.sdk.models.Reference;
import io.sphere.sdk.test.IntegrationTest;
import io.sphere.sdk.utils.ListUtils;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static io.sphere.sdk.cartdiscounts.CartDiscountFixtures.withCartDiscount;
import static io.sphere.sdk.cartdiscounts.CartDiscountFixtures.withPersistentCartDiscount;
import static io.sphere.sdk.discountcodes.DiscountCodeFixtures.withPersistentDiscountCode;
import static io.sphere.sdk.test.SphereTestUtils.*;
import static io.sphere.sdk.utils.ListUtils.listOf;
import static org.assertj.core.api.Assertions.*;

public class DiscountCodeUpdateCommandTest extends IntegrationTest {
    @Test
    public void setName() throws Exception {
        withPersistentDiscountCode(client(), discountCode -> {
            final LocalizedStrings newName = randomSlug();
            final DiscountCode updatedDiscountCode =
                    execute(DiscountCodeUpdateCommand.of(discountCode, SetName.of(newName)));
            assertThat(updatedDiscountCode.getName()).contains(newName);
        });
    }
    
    @Test
    public void setDescription() throws Exception {
        withPersistentDiscountCode(client(), discountCode -> {
            final LocalizedStrings newDescription = randomSlug();
            final DiscountCode updatedDiscountCode =
                    execute(DiscountCodeUpdateCommand.of(discountCode, SetDescription.of(newDescription)));
            assertThat(updatedDiscountCode.getDescription()).contains(newDescription);
        });
    }

    @Test
    public void setCartPredicate() throws Exception {
        withPersistentDiscountCode(client(), discountCode -> {
            final String predicateAsString =
                    //you need to change the predicate
                    discountCode.getCartPredicate().map(p -> "1 = 1".equals(p)).orElse(false) ? "true = true" : "1 = 1";

            final CartPredicate cartPredicate = CartPredicate.of(predicateAsString);
            final DiscountCode updatedDiscountCode =
                    execute(DiscountCodeUpdateCommand.of(discountCode, SetCartPredicate.of(cartPredicate)));
            assertThat(updatedDiscountCode.getCartPredicate()).contains(cartPredicate.toSphereCartPredicate());
        });
    }

    @Test
    public void setMaxApplications() throws Exception {
        withPersistentDiscountCode(client(), discountCode -> {
            final long maxApplications = randomLong();
            final DiscountCode updatedDiscountCode =
                    execute(DiscountCodeUpdateCommand.of(discountCode, SetMaxApplications.of(maxApplications)));
            assertThat(updatedDiscountCode.getMaxApplications()).contains(maxApplications);
        });
    }
    
    @Test
    public void setMaxApplicationsPerCustomer() throws Exception {
        withPersistentDiscountCode(client(), discountCode -> {
            final long maxApplications = randomLong();
            final DiscountCode updatedDiscountCode =
                    execute(DiscountCodeUpdateCommand.of(discountCode, SetMaxApplicationsPerCustomer.of(maxApplications)));
            assertThat(updatedDiscountCode.getMaxApplicationsPerCustomer()).contains(maxApplications);
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    public void changeCartDiscounts() throws Exception {
        withPersistentCartDiscount(client(), randomKey(), cartDiscount ->
            withPersistentDiscountCode(client(), discountCode -> {
                final List<Reference<CartDiscount>> oldCartDiscounts = discountCode.getCartDiscounts();
                assertThat(oldCartDiscounts).doesNotContain(cartDiscount.toReference());

                final List<Reference<CartDiscount>> newDiscountsList =
                        listOf(oldCartDiscounts, cartDiscount.toReference());

                final DiscountCode updatedDiscountCode =
                        execute(DiscountCodeUpdateCommand.of(discountCode, ChangeCartDiscounts.of(newDiscountsList)));
                assertThat(updatedDiscountCode.getCartDiscounts()).isEqualTo(newDiscountsList);

                //clean up test
                execute(DiscountCodeUpdateCommand.of(updatedDiscountCode, ChangeCartDiscounts.of(oldCartDiscounts)));
            })
        );
    }
}