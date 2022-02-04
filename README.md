The dependencies of some of the beans in the application context form a cycle:

   jwtRequestFilter (field private com.spring.service.user.UserDetailServiceImpl com.spring.config.jwt.JwtRequestFilter.userDetailServiceImp)
┌─────┐
|  userDetailServiceImpl (field private com.spring.repositories.UserRepo com.spring.service.user.UserDetailServiceImpl.userRepo)
↑     ↓
|  userRepo defined in com.spring.repositories.UserRepo defined in @EnableElasticsearchRepositories declared on RestTransportClientConfig
↑     ↓
|  elasticsearchOperations defined in class path resource [com/spring/config/elasticsearch/RestTransportClientConfig.class]
└─────┘
