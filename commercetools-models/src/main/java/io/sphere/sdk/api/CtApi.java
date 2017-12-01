package io.sphere.sdk.api;

import io.sphere.sdk.api.internal.*;
import io.sphere.sdk.client.SphereClient;

public interface CtApi extends SphereClientContainer {

    static CtApi of(SphereClient sphereClient){
        return new CtApiImpl(sphereClient);
    }

    //CRUD operations
    default CreateApi create(){
        return new CreateApiImpl(getSphereClient());
    }

    default UpdateApi update() {
        return new UpdateApiImpl(getSphereClient());
    }

    default DeleteApi delete(){
        return new DeleteApiImpl(getSphereClient());
    }

    default QueryApi query(){
        return new QueryApiImpl(getSphereClient());
    }

    //Extension for CRUD operations
    default SearchApi search(){
        return new SearchApiImpl(getSphereClient());
    }


    //Some Commands that arent easily interpreted as updates for ex request new password
    default CommandApi command(){
        return new CommandApiImpl(getSphereClient());
    }




}
