package configurations

import ddd.DomaineService
import ddd.Stub
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import starwars.domain.Fleet

@Configuration
@ComponentScan(basePackageClasses = [Fleet::class],
    includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [DomaineService::class, Stub::class])])
open class DomainConfiguration {
}
