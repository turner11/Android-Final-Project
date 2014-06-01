package com.finalProject.smstranslator;

@SuppressWarnings("rawtypes")
public class Language implements java.lang.Comparable
{

		private String _symbol;
		/** 
		 Gets the language symbol.
		*/
		public final String getSymbol()
		{
			return _symbol;
		}
		public final void setSymbol(String value)
		{
			this._symbol = value;
		}

		private String _nativeName;
		/** 
		 Gets the native name of the language.
		 
		 <value>
		 The the native name of the language.
		 </value>
		*/
		public final String getNativeName()
		{
			return _nativeName;
		}
		public final void setNativeName(String value)
		{
			this._nativeName = value;
		}

		private String _englishName;
		/** 
		 Gets the English name of the language.
		 
		 <value>
		 The the English name of the language.
		 </value>
		*/
		public final String getEnglishName()
		{
			return _englishName;
		}
		public final void setEnglishName(String value)
		{
			this._englishName = value;
		}

		private boolean _isRightToLeft;
		/** 
		 Gets or sets a value indicating whether this language is right to left.
		 
		 <value>
		 	<b>true</b> if this language is right to left; otherwise, <c>false</c>.
		 </value>
		*/
		public final boolean getIsRightToLeft()
		{
			return _isRightToLeft;
		}
		public final void setIsRightToLeft(boolean value)
		{
			_isRightToLeft = value;
		}


		/** 
		 Initializes a new instance of the <see cref="Language"/> class.
		 
		 @param symbol The symbol.
		 @param nativeName Name of the native.
		 @param englishName Name of the english.
		*/
		public Language(String symbol, String nativeName, String englishName)
		{
			this._symbol = symbol;
			this._nativeName = nativeName;
			this._englishName = englishName;
		}

		/** 
		 Returns a <see cref="System.String"/> that represents this instance.
		 
		 @return 
		 A <see cref="System.String"/> that represents this instance.
		 
		*/
		@Override
		public String toString()
		{
			return String.format("%1$s, %2$s (%3$s)", getSymbol(), getEnglishName(), getNativeName());
		}

		/** 
		 Determines whether the specified <see cref="System.Object" /> is equal to this instance.
		 
		 @param obj The <see cref="System.Object" /> to compare with this instance.
		 @return 
		   <c>true</c> if the specified <see cref="System.Object" /> is equal to this instance; otherwise, <c>false</c>.
		 
		*/
		@Override
		public boolean equals(Object obj)
		{
			Language other = (Language)((obj instanceof Language) ? obj : null);
			if (other == null)
			{
				return false;
			}
		
			return this.getSymbol().equals(other.getSymbol());		
		}

		/** 
		 Returns a hash code for this instance.
		 
		 @return 
		 A hash code for this instance, suitable for use in hashing algorithms and data structures like a hash table. 
		 
		*/
		@Override
		public int hashCode()
		{
			return super.hashCode();
		}



		public final int compareTo(Object obj)
		{
			Language other = (Language)((obj instanceof Language) ? obj : null);
			if (other == null)
			{
				throw new IllegalArgumentException("Cannot compare non Language object to Language.");
			}
			return this._englishName.compareTo(other._englishName);
		}





}