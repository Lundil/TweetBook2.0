create function lengthOfPassword()
	returns trigger as '
		begin
		if length(new.password) < 6 then
			raise exception ''le mot de passe doit contenir au moins six cacateres'' 
		end if;
		return new;
		end;'
	language 'plpsql';