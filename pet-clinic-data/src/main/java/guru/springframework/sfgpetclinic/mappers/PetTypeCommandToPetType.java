/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.commands.PetTypeCommand;
import guru.springframework.sfgpetclinic.model.PetType;
import lombok.Synchronized;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
@Component
public class PetTypeCommandToPetType implements Converter<PetTypeCommand, PetType> {
	
	@Synchronized
	@Nullable
	@Override
	public PetType convert(PetTypeCommand source) {
		if (source == null) {
			return null;
		}
		
		final PetType result = new PetType();
		result.setId(source.getId());
		result.setName(source.getName());
		
		return result;
	}	
}
