/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.model.Visit;
import lombok.Synchronized;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
public class VisitCommandToVisit implements Converter<VisitCommand, Visit> {
	
	@Synchronized
	@Nullable
	@Override
	public Visit convert(VisitCommand source) {
		if (source == null) {
			return null;
		}

		final Visit result = new Visit();
		result.setId(source.getId());
		result.setDate(source.getDate());
		result.setDescription(source.getDescription());
		return result;
	}
}
